package com.kara4k.mediagrub.view.tumblr;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.ServiceNavigation;
import com.kara4k.mediagrub.view.tumblr.custom.TumblrCustomUsersListFragment;

public class TumblrServiceNavigation implements ServiceNavigation {

    private static TumblrServiceNavigation sTumblrServiceNavigation;

    public static TumblrServiceNavigation getInstance() {
        if (sTumblrServiceNavigation == null) {
            sTumblrServiceNavigation = new TumblrServiceNavigation();
        }
        return sTumblrServiceNavigation;
    }

    private TumblrServiceNavigation() {
    }

    @Override
    public Fragment getMainFragment() {
        return TumblrCustomUsersListFragment.newInstance();
    }

    @Override
    public void onUpdateMenu(Menu menu) {
        menu.findItem(R.id.service_item_auth).setVisible(false);
        menu.findItem(R.id.service_item_friends).setVisible(false);
        menu.findItem(R.id.service_item_custom_friends).setVisible(true);
        menu.findItem(R.id.service_item_search).setVisible(false);
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
        return TumblrCustomUsersListFragment.newInstance();
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
        return R.id.nav_menu_item_tumblr;
    }
}
