package com.kara4k.mediagrub.download;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.util.LongSparseArray;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.modules.AppModule;
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

    private static final int NOTIFICATION_UPDATE = 1000;

    private final Context mContext;
    private final TaskDao mTaskDao;
    private final MediaItemDao mMediaItemDao;
    private final NotificationManagerCompat mNotifyManager;

    private final LongSparseArray<Task> mTasksArray = new LongSparseArray<>();
    private final LongSparseArray<Boolean> mRunningArray = new LongSparseArray<>();
    private final LongSparseArray<Boolean> mStoppedArray = new LongSparseArray<>();
    private final LongSparseArray<Boolean> mPausedArray = new LongSparseArray<>();

    private final LongSparseArray<NotificationCompat.Builder> mBuilders = new LongSparseArray<>();

    private int notifyId = 1;

    @Inject
    public DownloadManager(final Context context, final DaoSession daoSession, final NotificationManagerCompat notificationManagerCompat) {
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

    public boolean isInitialized(final long taskId) {
        return mTasksArray.get(taskId, null) != null;
    }

    public boolean isPaused(final long taskId) {
        return mPausedArray.get(taskId, false);
    }

    public boolean hasActiveTasks() {
        return mTasksArray.size() != 0;
    }

    void pauseTask(final long taskId) {
        setTaskRunning(taskId, false);
        mPausedArray.put(taskId, true);
        mRunningArray.remove(taskId);
        showNotify(taskId);
    }

    void resumeTask(final long taskId) {
        setTaskRunning(taskId, true);
        mRunningArray.put(taskId, true);
        mPausedArray.remove(taskId);
        mStoppedArray.remove(taskId);
        showNotify(taskId);
    }

    public void stopTask(final long taskId) {
        final Task task = mTasksArray.get(taskId, null);
        if (task == null) return;
        mStoppedArray.put(taskId, true);
        mRunningArray.remove(taskId);
        mPausedArray.remove(taskId);
        hideNotify(taskId);
    }

    private void hideNotify(final long taskId) {
        final Task task = mTasksArray.get(taskId);
        if (task != null) {
            mNotifyManager.cancel(task.getNotificationId());
        }
    }

    private void showNotify(final long taskId) {
        final Task task = mTasksArray.get(taskId, null);
        if (task == null) return;

        createTaskNotification(task);
    }

    private void createTaskNotification(final Task task) {
        final NotificationCompat.Builder builder = createBuilder(task);
        mBuilders.put(task.getId(), builder);
        mNotifyManager.notify(task.getNotificationId(), builder.build());
    }

    private void setTaskRunning(final long taskId, final boolean isRunning) {
        final Task task = mTasksArray.get(taskId);
        if (task != null) {
            task.setIsRunning(isRunning);
            mTaskDao.update(task);
        }
    }

    private void startTasks(final List<Task> taskList) {
        Observable.fromIterable(taskList)
                .subscribeOn(Schedulers.io())
                .subscribe(this::startTask, Throwable::printStackTrace);
    }

    private void startTask(final Task task) {
        Completable.fromAction(() -> initTask(task))
                .subscribeOn(Schedulers.computation())
                .subscribe(() -> startDownload(mTasksArray.get(task.getId())), Throwable::printStackTrace);
    }

    public void startTask(final long taskId) {
        Completable.fromAction(() -> initTask(taskId))
                .subscribeOn(Schedulers.computation())
                .subscribe(() -> startDownload(mTasksArray.get(taskId)), Throwable::printStackTrace);
    }

    private void initTask(final long taskId) {
        final Task task = mTaskDao.queryBuilder().where(TaskDao.Properties.Id.eq(taskId)).unique();
        initTask(task);
    }

    private void initTask(final Task task) {
        task.setCount(task.getTotal() - task.getMediaItems().size());
        task.setNotificationId(notifyId++);
        mTasksArray.put(task.getId(), task);
        createTaskNotification(task);
    }

    private void startDownload(final Task task) {
        final Long taskId = task.getId();
        mRunningArray.put(taskId, true);
        setTaskRunning(taskId, true);

        final Disposable[] disposable = new Disposable[1];
        final long[] time = {System.currentTimeMillis()};

        Observable.fromIterable(task.getMediaItems())
                .takeWhile(this::stopTask)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<MediaItem>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        disposable[0] = d;
                    }

                    @Override
                    public void onNext(final MediaItem mediaItem) {
                        if (mStoppedArray.get(taskId, false)) {
                            return;
                        }

                        while (mPausedArray.get(taskId, false)) {
                            try {
                                Thread.sleep(1000);
                                if (mStoppedArray.get(taskId, false)) {
                                    return;
                                }
                            } catch (final InterruptedException e) {
                                mNotifyManager.cancel(task.getNotificationId());
                            }
                        }

                        int count = task.getCount();

                        try {
                            downloadItem(task, mediaItem);
                        } catch (final Exception e) {
                            e.printStackTrace();
                            return;
                        }

                        ++count;
                        task.setCount(count);

                        final long currentStamp = System.currentTimeMillis();
                        if (currentStamp - time[0] > NOTIFICATION_UPDATE) {
                            sendProgressBroadcast(task);
                            updateProgressNotification(task);
                            time[0] = currentStamp;
                        }

                        mMediaItemDao.delete(mediaItem);
                    }

                    @Override
                    public void onError(final Throwable e) {
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

    private boolean stopTask(final MediaItem mediaItem) throws Exception {
        final Boolean isStopped = mStoppedArray.get(mediaItem.getMTaskId(), false);
        return !isStopped;
    }

    private NotificationCompat.Builder createBuilder(final Task task) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, AppModule.NOTIFICATION_CHANNEL_ID);
        final String textProgress = String.format(Locale.ENGLISH, "%d/%d",
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

    private void updateProgressNotification(final Task task) {
        final Long taskId = task.getId();
        final NotificationCompat.Builder builder = mBuilders.get(taskId);

        builder.setProgress(task.getTotal(), task.getCount(), false);
        builder.setContentInfo(String.format(Locale.ENGLISH, "%d/%d", task.getCount(), task.getTotal()));
        mNotifyManager.notify(task.getNotificationId(), builder.build());
    }

    private void downloadItem(final Task task, final MediaItem mediaItem) throws IOException {
        final String subPath = task.getSubPath();
        if (!isPathExist(subPath)) return;

        final String savePath = getSavePath(subPath, mediaItem);
        if (isFileExist(savePath)) return;

        downloadItem(mediaItem, savePath);
        sendMediaScanBroadcast(savePath);
    }

    private void sendMediaScanBroadcast(final String savePath) {
        final Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        final File file = new File(savePath);

        if (file.exists()) {
            final Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            mContext.sendBroadcast(mediaScanIntent);
        }
    }

    private void downloadItem(final MediaItem mediaItem, final String savePath) throws IOException {
        final URL url = new URL(mediaItem.getSourceUrl());

        try (final InputStream input = new BufferedInputStream(url.openStream())) {

            try (final OutputStream output = new BufferedOutputStream(new FileOutputStream(savePath))) {
                final byte[] buffer = new byte[1024];
                int bytesRead = 0;

                while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                    output.write(buffer, 0, bytesRead);
                }

            }
        }
    }

    public static String getSavePath(final String subPath, final MediaItem mediaItem) {
        String extension = ".jpg";

        if (mediaItem.getType() == MediaItem.VIDEO) extension = ".mp4";

        return String.format(Locale.ENGLISH,
                "%s/%s%s", subPath, mediaItem.getId(), extension);
    }

    private boolean isFileExist(final String savePath) {
        final File file = new File(savePath);
        return file.exists();
    }

    private boolean isPathExist(final String subPath) {
        final File file = new File(subPath);
        return file.exists() || file.mkdirs();
    }

    private NotificationCompat.Action getPauseAction(final Task task) {
        final int actionIcon;
        final String actionText;
        final int action;

        if (mPausedArray.get(task.getId(), false)) {
            actionIcon = R.drawable.ic_play_arrow_black_24dp;
            actionText = mContext.getString(R.string.notif_action_start);
            action = DownloadsReceiver.RESUME_TASK;
        } else {
            actionIcon = R.drawable.ic_pause_black_24dp;
            actionText = mContext.getString(R.string.notif_action_pause);
            action = DownloadsReceiver.PAUSE_TASK;
        }
        final PendingIntent pi = getActionPi(task, action);

        return new NotificationCompat.Action(actionIcon, actionText, pi);
    }

    private NotificationCompat.Action getStopAction(final Task task) {
        final int actionIcon = R.drawable.ic_stop_black_24dp;
        final String actionText = mContext.getString(R.string.notif_action_stop);
        final int action = DownloadsReceiver.STOP_TASK;
        final PendingIntent pi = getActionPi(task, action);

        return new NotificationCompat.Action(actionIcon, actionText, pi);
    }

    private PendingIntent getActionPi(final Task task, final int action) {
        final int requestCode = task.getNotificationId() * 10 + action;
        final Intent intent = DownloadsReceiver.getActionIntent(task.getId(), action);
        return PendingIntent.getBroadcast(mContext, requestCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void sendProgressBroadcast(final Task task) {
        final Intent intent = DownloadsReceiver.getProgressIntent(task);
        mContext.sendBroadcast(intent);
    }

    private void sendTaskFinishedBroadcast(final long taskId) {
        final Intent intent = DownloadsReceiver.getActionIntent(taskId, DownloadsReceiver.TASK_FINISHED);
        mContext.sendBroadcast(intent);
    }

    private void sendTaskCompletedBroadcast(final long taskId) {
        final Intent intent = DownloadsReceiver.getActionIntent(taskId, DownloadsReceiver.TASK_COMPLETED);
        mContext.sendBroadcast(intent);
    }
}
