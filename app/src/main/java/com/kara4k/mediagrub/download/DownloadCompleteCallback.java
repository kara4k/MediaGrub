package com.kara4k.mediagrub.download;


public abstract class DownloadCompleteCallback implements DownloadsReceiver.ReceiverCallback {

    @Override
    public void onResumeTask(long taskId) {

    }

    @Override
    public void onPauseTask(long taskId) {

    }

    @Override
    public void onStopTask(long taskId) {

    }

    @Override
    public void onProgress(long taskId, int progress, int max) {

    }

    @Override
    public void onTaskFinished(long taskId) {

    }
}
