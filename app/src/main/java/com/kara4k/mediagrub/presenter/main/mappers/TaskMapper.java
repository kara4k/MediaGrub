package com.kara4k.mediagrub.presenter.main.mappers;


import com.kara4k.mediagrub.model.database.Task;
import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class TaskMapper implements Function<List<Task>, Observable<TaskViewObj>> {

    @Inject
    public TaskMapper() {
    }

    @Override
    public Observable<TaskViewObj> apply(List<Task> tasks) throws Exception {
        return Observable.fromIterable(tasks).map(this::map);
    }

    public TaskViewObj map(Task task) throws Exception {
        TaskViewObj taskViewObj = new TaskViewObj();
        taskViewObj.setId(task.getId());
        taskViewObj.setTitle(task.getUser());
        taskViewObj.setService(task.getService());
        taskViewObj.setSummary(task.getAlbum());
        taskViewObj.setTotal(task.getTotal());
        taskViewObj.setCount(task.getCount());
        taskViewObj.setSubPath(task.getSubPath());
        taskViewObj.setFirstFile(task.getFirstFile());
        taskViewObj.setType(task.getType());
        taskViewObj.setRunning(task.getIsRunning());
        return taskViewObj;
    }
}
