package com.kara4k.mediagrub.presenter.main;


import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.database.Task;
import com.kara4k.mediagrub.model.database.TaskDao;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.presenter.main.mappers.TaskMapper;
import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;
import com.kara4k.mediagrub.view.main.downloads.CompletedTasksViewIF;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompletedTasksPresenter extends ListPresenter<TaskViewObj, CompletedTasksViewIF> {

    @Inject
    TaskMapper mTaskMapper;
    private TaskDao mTaskDao;

    @Inject
    public CompletedTasksPresenter(DaoSession daoSession) {
        mTaskDao = daoSession.getTaskDao();
    }

    @Override
    protected boolean searchFilter(TaskViewObj taskViewObj, String text) {
        String title = taskViewObj.getTitle() != null
                ? taskViewObj.getTitle().toLowerCase() : EMPTY;
        String summary = taskViewObj.getSummary() != null
                ? taskViewObj.getSummary().toLowerCase() : EMPTY;
        String query = text.toLowerCase();

        return title.contains(query) || summary.contains(query);
    }

    public void onStart() {
        Observable.fromCallable(this::getCompletedTasks)
                .flatMap(mTaskMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    private List<Task> getCompletedTasks() {
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.IsCompleted.eq(true))
                .orderDesc(TaskDao.Properties.Id)
                .build().list();
    }

    @Override
    public void onItemClicked(int position, TaskViewObj taskViewObj) {
        getView().showFolderContent(taskViewObj.getFirstFile());
    }

    public void onNewTaskCompleted(long taskId) {
        Single.fromCallable(() -> getTask(taskId))
                .map(mTaskMapper::map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addCompletedTask, this::onError);
    }

    private void addCompletedTask(TaskViewObj taskViewObj) {
        getAllItems().add(0, taskViewObj);
        getView().setItems(getAllItems());
    }


    private Task getTask(long taskId) {
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.Id.eq(taskId))
                .build().unique();
    }

    @Override
    public void onDeleteSelectedConfirm() {
        Completable.fromAction(this::deleteSelectedTasks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDeleteSuccess, this::onDeleteError);
    }

    private void onDeleteSuccess() {
        getView().finishActionMode();
        onStart();
    }

    private void onDeleteError(Throwable throwable) {
        getView().finishActionMode();
        onError(throwable);
    }

    private void deleteSelectedTasks() {
        List<TaskViewObj> selectedItems = mSelector.getSelectedItems();
        List<Long> tasksId = new ArrayList<>(selectedItems.size());

        for (int i = 0; i < selectedItems.size(); i++) {
            Long id = selectedItems.get(i).getId();
            tasksId.add(id);
        }

        mTaskDao.deleteByKeyInTx(tasksId);
    }
}
