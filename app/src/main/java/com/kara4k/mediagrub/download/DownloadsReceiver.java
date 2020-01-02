package com.kara4k.mediagrub.download;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.kara4k.mediagrub.model.database.Task;

public class DownloadsReceiver extends BroadcastReceiver {

    public static final String ACTION_MANAGE_TASKS = "manage_download_tasks";
    public static final int EMPTY = -1;

    public static final String TASK_ID = "task_id";
    public static final String TASK_ACTION = "task_action";
    public static final String PROGRESS = "progress";
    private static final String MAX = "max";

    public static final int RESUME_TASK = 1;
    public static final int PAUSE_TASK = 2;
    public static final int STOP_TASK = 3;
    public static final int TASK_PROGRESS = 4;
    public static final int TASK_FINISHED = 5;
    public static final int TASK_COMPLETED = 6;

    private final ReceiverCallback mCallback;

    public DownloadsReceiver(final ReceiverCallback callback) {
        mCallback = callback;
    }

    public interface ReceiverCallback {

        void onResumeTask(long taskId);

        void onPauseTask(long taskId);

        void onStopTask(long taskId);

        void onProgress(long taskId, int progress, int max);

        void onTaskFinished(long taskId);

        void onTaskComplete(long taskId);
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final long taskId = intent.getLongExtra(TASK_ID, EMPTY);

        if (taskId == EMPTY || mCallback == null) return;

        final int taskAction = intent.getIntExtra(TASK_ACTION, EMPTY);

        switch (taskAction) {
            case RESUME_TASK:
                mCallback.onResumeTask(taskId);
                break;
            case PAUSE_TASK:
                mCallback.onPauseTask(taskId);
                break;
            case STOP_TASK:
                mCallback.onStopTask(taskId);
                break;
            case TASK_PROGRESS:
                final int progress = intent.getIntExtra(PROGRESS, EMPTY);
                final int max = intent.getIntExtra(MAX, EMPTY);
                if (progress != EMPTY || max != EMPTY) mCallback.onProgress(taskId, progress, max);
                break;
            case TASK_FINISHED:
                mCallback.onTaskFinished(taskId);
                break;
            case TASK_COMPLETED:
                mCallback.onTaskComplete(taskId);
                break;
        }
    }

    public static IntentFilter getIntentFilter() {
        return new IntentFilter(ACTION_MANAGE_TASKS);
    }

    public static Intent getActionIntent(final long taskId, final int action) {
        final Intent intent = new Intent(DownloadsReceiver.ACTION_MANAGE_TASKS);
        intent.putExtra(DownloadsReceiver.TASK_ID, taskId);
        intent.putExtra(DownloadsReceiver.TASK_ACTION, action);
        return intent;
    }

    public static Intent getProgressIntent(final Task task) {
        final Intent intent = new Intent(ACTION_MANAGE_TASKS);
        intent.putExtra(DownloadsReceiver.TASK_ID, task.getId());
        intent.putExtra(DownloadsReceiver.TASK_ACTION, DownloadsReceiver.TASK_PROGRESS);
        intent.putExtra(DownloadsReceiver.PROGRESS, task.getCount());
        intent.putExtra(DownloadsReceiver.MAX, task.getTotal());
        return intent;
    }
}
