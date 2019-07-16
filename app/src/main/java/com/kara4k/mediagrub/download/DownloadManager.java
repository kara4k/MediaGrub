package com.kara4k.mediagrub.download;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.LongSparseArray;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.database.MediaItemDao;
import com.kara4k.mediagrub.model.database.Task;
import com.kara4k.mediagrub.model.database.TaskDao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DownloadManager {

    private static final String CHANNEL_ID = "notify_channel";
    private static final int NOTIFICATION_UPDATE = 1000;

    private Context mContext;
    private TaskDao mTaskDao;
    private MediaItemDao mMediaItemDao;
    private NotificationManagerCompat mNotifyManager;

    private LongSparseArray<Task> mTasksArray = new LongSparseArray<>();
    private LongSparseArray<Boolean> mRunningArray = new LongSparseArray<>();
    private LongSparseArray<Boolean> mStoppedArray = new LongSparseArray<>();
    private LongSparseArray<Boolean> mPausedArray = new LongSparseArray<>();

    private LongSparseArray<NotificationCompat.Builder> mBuilders = new LongSparseArray<>();

    private int notifyId = 1;

    @Inject
    public DownloadManager(Context context, DaoSession daoSession, NotificationManagerCompat notificationManagerCompat) {
        mContext = context;
        mTaskDao = daoSession.getTaskDao();
        mMediaItemDao = daoSession.getMediaItemDao();
        mNotifyManager = notificationManagerCompat;
    }

    public void checkTasks() {
        Single.fromCallable(this::getRunnedTasks)
                .subscribeOn(Schedulers.io())
                .subscribe(this::startTasks, Throwable::printStackTrace);
    }

    private List<Task> getRunnedTasks() {
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.IsRunning.eq(true))
                .build().list();
    }

    public boolean isInitialized(long taskId) {
        return mTasksArray.get(taskId, null) != null;
    }

    public boolean isPaused(long taskId) {
        return mPausedArray.get(taskId, false);
    }

    public boolean hasActiveTasks() {
        return mTasksArray.size() != 0;
    }

    void pauseTask(long taskId) {
        setTaskRunning(taskId, false);
        mPausedArray.put(taskId, true);
        mRunningArray.remove(taskId);
        showNotify(taskId);
    }

    void resumeTask(long taskId) {
        setTaskRunning(taskId, true);
        mRunningArray.put(taskId, true);
        mPausedArray.remove(taskId);
        mStoppedArray.remove(taskId);
        showNotify(taskId);
    }

    public void stopTask(long taskId) {
        Task task = mTasksArray.get(taskId, null);
        if (task == null) return;
        mStoppedArray.put(taskId, true);
        mRunningArray.remove(taskId);
        mPausedArray.remove(taskId);
        hideNotify(taskId);
    }

    private void hideNotify(long taskId) {
        Task task = mTasksArray.get(taskId);
        if (task != null) {
            mNotifyManager.cancel(task.getNotificationId());
        }
    }

    private void showNotify(long taskId) {
        Task task = mTasksArray.get(taskId, null);
        if (task == null) return;

        createTaskNotification(task);
    }

    private void createTaskNotification(Task task) {
        NotificationCompat.Builder builder = createBuilder(task);
        mBuilders.put(task.getId(), builder);
        mNotifyManager.notify(task.getNotificationId(), builder.build());
    }

    private void setTaskRunning(long taskId, boolean isRunning) {
        Task task = mTasksArray.get(taskId);
        if (task != null) {
            task.setIsRunning(isRunning);
            mTaskDao.update(task);
        }
    }

    private void startTasks(List<Task> taskList) {
        Observable.fromIterable(taskList)
                .subscribeOn(Schedulers.io())
                .subscribe(this::startTask, Throwable::printStackTrace);
    }

    private void startTask(Task task) {
        Completable.fromAction(() -> initTask(task))
                .subscribeOn(Schedulers.computation())
                .subscribe(() -> startDownload(mTasksArray.get(task.getId())), Throwable::printStackTrace);
    }

    public void startTask(long taskId) {
        Completable.fromAction(() -> initTask(taskId))
                .subscribeOn(Schedulers.computation())
                .subscribe(() -> startDownload(mTasksArray.get(taskId)), Throwable::printStackTrace);
    }

    private void initTask(long taskId) {
        Task task = mTaskDao.queryBuilder().where(TaskDao.Properties.Id.eq(taskId)).unique();
        initTask(task);
    }

    private void initTask(Task task) {
        task.setCount(task.getTotal() - task.getMediaItems().size());
        task.setNotificationId(notifyId++);
        mTasksArray.put(task.getId(), task);
        createTaskNotification(task);
    }

    private void startDownload(Task task) {
        Long taskId = task.getId();
        mRunningArray.put(taskId, true);
        setTaskRunning(taskId, true);

        final Disposable[] disposable = new Disposable[1];
        final long[] time = {System.currentTimeMillis()};

        Observable.fromIterable(task.getMediaItems())
                .takeWhile(this::stopTask)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<MediaItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable[0] = d;
                    }

                    @Override
                    public void onNext(MediaItem mediaItem) {
                        if (mStoppedArray.get(taskId, false)) {
                            return;
                        }

                        while (mPausedArray.get(taskId, false)) {
                            try {
                                Thread.sleep(1000);
                                if (mStoppedArray.get(taskId, false)) {
                                    return;
                                }
                            } catch (InterruptedException e) {
                                mNotifyManager.cancel(task.getNotificationId());
                            }
                        }

                        int count = task.getCount();

                        try {
                            downloadItem(task, mediaItem);
                        } catch (Exception e) {
                            return;
                        }

                        ++count;
                        task.setCount(count);

                        long currentStamp = System.currentTimeMillis();
                        if (currentStamp - time[0] > NOTIFICATION_UPDATE) {
                            sendProgressBroadcast(task);
                            updateProgressNotification(task);
                            time[0] = currentStamp;
                        }

                        mMediaItemDao.delete(mediaItem);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        mNotifyManager.cancel(task.getNotificationId());

                        mTasksArray.remove(taskId);
                        mRunningArray.remove(taskId);
                        mStoppedArray.remove(taskId);
                        mPausedArray.remove(taskId);
                        mBuilders.remove(taskId);

                        sendProgressBroadcast(task);
                        if (task.getCount() >= task.getTotal()) {
                            task.setIsCompleted(true);
                            sendTaskCompletedBroadcast(taskId);
                        } else {
                            sendTaskFinishedBroadcast(taskId);
                        }

                        task.setIsRunning(false);
                        mTaskDao.update(task);
                        mTaskDao.detach(task);

                        disposable[0].dispose();
                    }
                });
    }

    private boolean stopTask(MediaItem mediaItem) throws Exception {
        Boolean isStopped = mStoppedArray.get(mediaItem.getMTaskId(), false);
        return !isStopped;
    }

    private NotificationCompat.Builder createBuilder(Task task) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID);
        String textProgress = String.format(Locale.ENGLISH, "%d/%d",
                task.getCount(), task.getTotal());

        builder.setContentTitle(task.getUser())
                .setOnlyAlertOnce(true)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_file_download_white_24dp)
                .setContentInfo(textProgress)
                .setProgress(task.getTotal(), task.getCount(), false)
                .addAction(getPauseAction(task))
                .addAction(getStopAction(task))
                .setContentText(task.getAlbum());
        return builder;
    }

    private void updateProgressNotification(Task task) {
        Long taskId = task.getId();
        NotificationCompat.Builder builder = mBuilders.get(taskId);

        builder.setProgress(task.getTotal(), task.getCount(), false);
        builder.setContentInfo(String.format(Locale.ENGLISH, "%d/%d", task.getCount(), task.getTotal()));
        mNotifyManager.notify(task.getNotificationId(), builder.build());
    }

    private void downloadItem(Task task, MediaItem mediaItem) throws IOException {
        String subPath = task.getSubPath();
        if (!isPathExist(subPath)) return;

        String savePath = getSavePath(subPath, mediaItem);
        if (isFileExist(savePath)) return;

        downloadItem(mediaItem, savePath);
        sendMediaScanBroadcast(savePath);
    }

    private void sendMediaScanBroadcast(String savePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(savePath);

        if (file.exists()) {
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            mContext.sendBroadcast(mediaScanIntent);
        }
    }

    private void downloadItem(MediaItem mediaItem, String savePath) throws IOException {
        URL url = new URL(mediaItem.getSourceUrl());

        try (InputStream input = new BufferedInputStream(url.openStream())) {

            try (OutputStream output = new BufferedOutputStream(new FileOutputStream(savePath))) {
                byte[] buffer = new byte[1024];
                int bytesRead = 0;

                while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                    output.write(buffer, 0, bytesRead);
                }

            }
        }
    }

    public static String getSavePath(String subPath, MediaItem mediaItem) {
        String extension = ".jpg";

        if (mediaItem.getType() == MediaItem.VIDEO) extension = ".mp4";

        return String.format(Locale.ENGLISH,
                "%s/%s%s", subPath, mediaItem.getId(), extension);
    }

    private boolean isFileExist(String savePath) {
        File file = new File(savePath);
        return file.exists();
    }

    private boolean isPathExist(String subPath) {
        File file = new File(subPath);
        return file.exists() || file.mkdirs();
    }

    private NotificationCompat.Action getPauseAction(Task task) {
        int actionIcon;
        String actionText;
        int action;

        if (mPausedArray.get(task.getId(), false)) {
            actionIcon = R.drawable.ic_play_arrow_black_24dp;
            actionText = mContext.getString(R.string.notif_action_start);
            action = DownloadsReceiver.RESUME_TASK;
        } else {
            actionIcon = R.drawable.ic_pause_black_24dp;
            actionText = mContext.getString(R.string.notif_action_pause);
            action = DownloadsReceiver.PAUSE_TASK;
        }
        PendingIntent pi = getActionPi(task, action);

        return new NotificationCompat.Action(actionIcon, actionText, pi);
    }

    private NotificationCompat.Action getStopAction(Task task) {
        int actionIcon = R.drawable.ic_stop_black_24dp;
        String actionText = mContext.getString(R.string.notif_action_stop);
        int action = DownloadsReceiver.STOP_TASK;
        PendingIntent pi = getActionPi(task, action);

        return new NotificationCompat.Action(actionIcon, actionText, pi);
    }

    private PendingIntent getActionPi(Task task, int action) {
        int requestCode = task.getNotificationId() * 10 + action;
        Intent intent = DownloadsReceiver.getActionIntent(task.getId(), action);
        return PendingIntent.getBroadcast(mContext, requestCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void sendProgressBroadcast(Task task) {
        Intent intent = DownloadsReceiver.getProgressIntent(task);
        mContext.sendBroadcast(intent);
    }

    private void sendTaskFinishedBroadcast(long taskId) {
        Intent intent = DownloadsReceiver.getActionIntent(taskId, DownloadsReceiver.TASK_FINISHED);
        mContext.sendBroadcast(intent);
    }

    private void sendTaskCompletedBroadcast(long taskId) {
        Intent intent = DownloadsReceiver.getActionIntent(taskId, DownloadsReceiver.TASK_COMPLETED);
        mContext.sendBroadcast(intent);
    }
}
