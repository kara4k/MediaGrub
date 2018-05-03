package com.kara4k.mediagrub.view.main.downloads;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.util.LongSparseArray;
import android.widget.LinearLayout;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.download.DownloadService;
import com.kara4k.mediagrub.download.DownloadsReceiver;
import com.kara4k.mediagrub.presenter.main.ActiveTasksPresenter;
import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;
import com.kara4k.mediagrub.view.base.BaseFragment;
import com.kara4k.mediagrub.view.custom.DownloadView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActiveTasksFragment extends BaseFragment
        implements ActiveTasksViewIF, DownloadsReceiver.ReceiverCallback {

    @BindView(R.id.main_layout)
    LinearLayout mLayout;

    @Inject
    ActiveTasksPresenter mPresenter;
    private DownloadsReceiver mReceiver = new DownloadsReceiver(this);
    private LongSparseArray<DownloadView> mViewsArray = new LongSparseArray<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_downloads;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.empty_menu;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectActiveTasksFragment(this);
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        bindService();
        getContext().registerReceiver(mReceiver, DownloadsReceiver.getIntentFilter());
        mPresenter.onStart();
    }

    private void bindService() {
        Intent intent = DownloadService.newIntent(getContext());
        ServiceConnection serviceConnection = mPresenter.getServiceConnection();
        DownloadService.setConnected(true);
        getActivity().startService(intent);
        getActivity().bindService(intent, serviceConnection, 0);
    }

    @Override
    public void showAllTasks(List<TaskViewObj> tasks) {
        Observable.fromIterable(tasks)
                .subscribeOn(Schedulers.computation())
                .map(this::map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DownloadView>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mViewsArray = new LongSparseArray<>();
                    }

                    @Override
                    public void onNext(DownloadView downloadView) {
                        mLayout.addView(downloadView);
                        mViewsArray.put(downloadView.getTaskId(), downloadView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private DownloadView map(TaskViewObj taskViewObj) throws Exception {
        DownloadView downloadView = new DownloadView(getContext());
        Long taskId = taskViewObj.getId();

        downloadView.setTaskId(taskId);
        downloadView.setTitle(taskViewObj.getTitle());
        downloadView.setService(taskViewObj.getService());
        downloadView.setSummary(taskViewObj.getSummary());
        downloadView.setProgress(taskViewObj.getCount(), taskViewObj.getTotal());
        if (taskViewObj.isRunning()) downloadView.setRunning();

        downloadView.setOnPlayPauseListener(view -> mPresenter.onPlayPauseTask(taskId));
        downloadView.setOnStopListener(view -> mPresenter.onStopTask(taskId));
        downloadView.setOnDeleteListener(view -> mPresenter.onDeleteTask(taskId));
        return downloadView;
    }

    @Override
    public void showDeleteConfirmation(Long taskId) {
        String title = getString(R.string.dialog_delete_title);
        String message = getString(R.string.dialog_delete_task_message);
        DialogInterface.OnClickListener okListener = (dialogInterface, i)
                -> mPresenter.onDeleteConfirm(taskId);

        showConfirmDialog(title, message, okListener);
    }

    @Override
    public void onTaskDeleted(Long taskId) {
        DownloadView downloadView = mViewsArray.get(taskId);
        mLayout.removeView(downloadView);
        mViewsArray.remove(taskId);
    }

    @Override
    public void onResumeTask(long taskId) {
        DownloadView downloadView = mViewsArray.get(taskId);
        if (downloadView == null) return;
        downloadView.setRunning();
    }

    @Override
    public void onPauseTask(long taskId) {
        DownloadView downloadView = mViewsArray.get(taskId);
        if (downloadView == null) return;
        downloadView.setPaused();
    }

    @Override
    public void onStopTask(long taskId) {
        DownloadView downloadView = mViewsArray.get(taskId);
        if (downloadView == null) return;
        downloadView.setPaused();
    }

    @Override
    public void onProgress(long taskId, int progress, int max) {
        DownloadView downloadView = mViewsArray.get(taskId);
        if (downloadView == null) return;
        downloadView.setProgress(progress, max);
    }

    @Override
    public void toggleTask(Long taskId) {
        DownloadView downloadView = mViewsArray.get(taskId);
        if (downloadView == null) return;
        downloadView.togglePlayPause();
    }

    @Override
    public void stopTask(Long taskId) {
        onStopTask(taskId);
    }

    @Override
    public void onTaskFinished(long taskId) {
        onStopTask(taskId);
    }

    @Override
    public void onTaskComplete(long taskId) {
        onTaskDeleted(taskId);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DownloadService.setConnected(false);
        getContext().unregisterReceiver(mReceiver);
        getActivity().unbindService(mPresenter.getServiceConnection());
    }

    public static ActiveTasksFragment newInstance() {
        Bundle args = new Bundle();
        ActiveTasksFragment fragment = new ActiveTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
