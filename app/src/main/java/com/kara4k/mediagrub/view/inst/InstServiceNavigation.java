package com.kara4k.mediagrub.view.inst;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.ServiceNavigation;
import com.kara4k.mediagrub.view.inst.custom.InstCustomUsersListFragment;
import com.kara4k.mediagrub.view.inst.search.InstPhotoSearchFragment;

public class InstServiceNavigation implements ServiceNavigation {

    private static InstServiceNavigation sInstServiceNavigation;

    public static InstServiceNavigation getInstance() {
        if (sInstServiceNavigation == null) {
            sInstServiceNavigation = new InstServiceNavigation();
        }
        return sInstServiceNavigation;
    }

    private InstServiceNavigation() {
    }

    @Override
    public Fragment getMainFragment() {
        return InstCustomUsersListFragment.newInstance();
    }

    @Override
    public void onUpdateMenu(Menu menu) {
        menu.findItem(R.id.service_item_auth).setVisible(false);
        menu.findItem(R.id.service_item_friends).setVisible(false);
        menu.findItem(R.id.service_item_custom_friends).setVisible(true);
        menu.findItem(R.id.service_item_search).setVisible(true);
        menu.findItem(R.id.service_item_own).setVisible(false);
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
        return InstCustomUsersListFragment.newInstance();
    }

    @Override
    public Fragment getSearch() {
        return InstPhotoSearchFragment.newInstance();
    }

    @Override
    public Fragment getOwn() {
        return null;
    }

    @Override
    public int getToolbarVisibility() {
        return View.VISIBLE;
    }

    @Override
    public int getMenuItemId() {
        return R.id.nav_menu_item_inst;
    }
}
