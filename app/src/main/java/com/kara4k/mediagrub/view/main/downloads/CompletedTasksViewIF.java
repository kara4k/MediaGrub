package com.kara4k.mediagrub.view.main.downloads;


import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;
import com.kara4k.mediagrub.view.base.ListViewIF;

public interface CompletedTasksViewIF extends ListViewIF<TaskViewObj> {

    void showFolderContent(String firstFile);
}
