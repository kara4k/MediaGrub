package com.kara4k.mediagrub.presenter.main;


import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.kara4k.mediagrub.download.DownloadService;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.database.MediaItemDao;
import com.kara4k.mediagrub.model.database.Task;
import com.kara4k.mediagrub.model.database.TaskDao;
import com.kara4k.mediagrub.presenter.base.Presenter;
import com.kara4k.mediagrub.presenter.main.mappers.TaskMapper;
import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;
import com.kara4k.mediagrub.view.main.downloads.ActiveTasksViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActiveTasksPresenter extends Presenter implements SingleObserver<List<TaskViewObj>> {

    @Inject
    ActiveTasksViewIF mView;
    @Inject
    TaskMapper mTaskMapper;
    private TaskDao mTaskDao;
    private MediaItemDao mMediaItemDao;
    private DownloadService mService;
    private ServiceConnection mServiceConnection;

    @Inject
    public ActiveTasksPresenter(DaoSession daoSession) {
        createServConn();
        mTaskDao = daoSession.getTaskDao();
        mMediaItemDao = daoSession.getMediaItemDao();
    }

    public void createServConn() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService = ((DownloadService.Binder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mService = null;
            }
        };
    }

    public void onStart(){
        Observable.fromCallable(this::getNotCompletedTasks)
                .flatMap(mTaskMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    private List<Task> getNotCompletedTasks() {
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.IsCompleted.eq(false))
                .orderDesc(TaskDao.Properties.Id)
                .build().list();
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mView.showError(e.getMessage());
    }

    @Override
    public void onSuccess(List<TaskViewObj> taskViewObjs) {
        mView.showAllTasks(taskViewObjs);
    }

    public void onPlayPauseTask(Long taskId) {
        mView.toggleTask(taskId);
        if (mService != null) {
            mService.toggleTask(taskId);
        }
    }

    public void onStopTask(Long taskId) {
        if (mService != null) {
            mService.onStopTask(taskId);
        }
        mView.stopTask(taskId);
    }

    public void onDeleteTask(Long taskId) {
        mView.showDeleteConfirmation(taskId);
    }

    public void onDeleteConfirm(Long taskId) {
        onStopTask(taskId);
        mView.onTaskDeleted(taskId);

        Completable.fromAction(() -> deleteTask(taskId))
                .subscribeOn(Schedulers.newThread())
                .subscribe(() -> {}, this::onError);
    }

    private void deleteTask(Long taskId) {
        Task task = mTaskDao.queryBuilder().where(TaskDao.Properties.Id.eq(taskId)).build().unique();
        mMediaItemDao.deleteInTx(task.getMediaItems());
        mTaskDao.delete(task);
    }

    public ServiceConnection getServiceConnection() {
        return mServiceConnection;
    }
}
