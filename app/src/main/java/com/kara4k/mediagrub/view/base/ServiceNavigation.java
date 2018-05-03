package com.kara4k.mediagrub.view.base;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;

public interface ServiceNavigation {

    Fragment getMainFragment();

    void onUpdateMenu(Menu menu);

    Intent getAuthIntent(Context context);

    Fragment getFriends();

    Fragment getCustomFriends();

    Fragment getSearch();

    Fragment getOwn();

    int getToolbarVisibility();

    int getMenuItemId();
}
