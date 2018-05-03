package com.kara4k.mediagrub.download;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

import com.kara4k.mediagrub.other.App;

import javax.inject.Inject;

public class DownloadService extends Service implements DownloadsReceiver.ReceiverCallback {

    @Inject
    DownloadManager mDownloadManager;
    @Inject
    NotificationManagerCompat mNotify;
    private Binder mBinder;

    private DownloadsReceiver mReceiver;
    private static boolean sIsConnected;

    public DownloadService() {
        mBinder = new Binder();
        mReceiver = new DownloadsReceiver(this);
    }

    public static void setConnected(boolean connected) {
        sIsConnected = connected;
    }

    public void onCreate() {
        super.onCreate();
        ((App) getApplication()).getAppComponent().injectDownloadService(this);
        registerReceiver(mReceiver, DownloadsReceiver.getIntentFilter());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!sIsConnected) {
            mDownloadManager.checkTasks();

            new Thread(() -> {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    stopSelf();
                }
                checkStopSelf();
            }).start();
        }
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        mNotify.cancelAll();

        if (!mDownloadManager.hasActiveTasks()) stopSelf();

        super.onTaskRemoved(rootIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void toggleTask(long taskId) {
        if (mDownloadManager.isInitialized(taskId)) {
            boolean isPaused = mDownloadManager.isPaused(taskId);

            if (isPaused) {
                mDownloadManager.resumeTask(taskId);
            } else {
                mDownloadManager.pauseTask(taskId);
            }
        } else {
            mDownloadManager.startTask(taskId);
        }
    }

    public void startTask(long taskId) {
        mDownloadManager.startTask(taskId);
    }

    @Override
    public void onResumeTask(long taskId) {
        mDownloadManager.resumeTask(taskId);
    }

    @Override
    public void onPauseTask(long taskId) {
        mDownloadManager.pauseTask(taskId);
    }

    @Override
    public void onStopTask(long taskId) {
        mDownloadManager.stopTask(taskId);
    }

    @Override
    public void onProgress(long taskId, int progress, int max) {
    }

    @Override
    public void onTaskFinished(long taskId) {
        if (!sIsConnected) checkStopSelf();
    }

    @Override
    public void onTaskComplete(long taskId) {
        if (!sIsConnected) checkStopSelf();
    }

    private void checkStopSelf() {
        if (!mDownloadManager.hasActiveTasks() && !sIsConnected) stopSelf();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        mNotify.cancelAll();
        super.onDestroy();
    }

    public class Binder extends android.os.Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, DownloadService.class);
    }
}
