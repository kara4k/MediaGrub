package com.kara4k.mediagrub.view.main.downloads;


import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;
import com.kara4k.mediagrub.view.base.ViewIF;

import java.util.List;

public interface ActiveTasksViewIF extends ViewIF {

    void showAllTasks(List<TaskViewObj> tasks);

    void onTaskDeleted(Long taskId);

    void showDeleteConfirmation(Long taskId);

    void toggleTask(Long taskId);

    void stopTask(Long taskId);
}
