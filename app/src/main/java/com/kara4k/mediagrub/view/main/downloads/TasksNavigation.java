package com.kara4k.mediagrub.view.main.downloads;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.ServiceNavigation;

public class TasksNavigation implements ServiceNavigation {

    private static TasksNavigation sTasksNavigation;

    public static TasksNavigation getInstance() {
        if (sTasksNavigation == null) {
            sTasksNavigation = new TasksNavigation();
        }
        return sTasksNavigation;
    }

    private TasksNavigation() {
    }

    @Override
    public Fragment getMainFragment() {
        return TasksViewPagerFragment.newInstance();
    }

    @Override
    public void onUpdateMenu(Menu menu) {

    }

    @Override
    public Intent getAuthIntent(Context context) {
        return null;
    }

    @Override
    public Fragment getFriends() {
        return null;
    }

    @Override
    public Fragment getCustomFriends() {
        return null;
    }

    @Override
    public Fragment getSearch() {
        return null;
    }

    @Override
    public Fragment getOwn() {
        return null;
    }

    @Override
    public int getToolbarVisibility() {
        return View.GONE;
    }

    @Override
    public int getMenuItemId() {
        return R.id.nav_menu_item_tasks;
    }
}
