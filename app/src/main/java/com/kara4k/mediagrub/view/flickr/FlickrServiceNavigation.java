package com.kara4k.mediagrub.view.flickr;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.ServiceNavigation;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomViewPagerFragment;
import com.kara4k.mediagrub.view.flickr.search.FlickrPhotoSearchFragment;

public class FlickrServiceNavigation implements ServiceNavigation {

    private static FlickrServiceNavigation sFlickrServiceNavigation;

    public static FlickrServiceNavigation getInstance() {
        if (sFlickrServiceNavigation == null) {
            sFlickrServiceNavigation = new FlickrServiceNavigation();
        }
        return sFlickrServiceNavigation;
    }

    private FlickrServiceNavigation() {
    }

    @Override
    public Fragment getMainFragment() {
        return FlickrCustomViewPagerFragment.newInstance();
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
        return FlickrCustomViewPagerFragment.newInstance();
    }

    @Override
    public Fragment getSearch() {
        return FlickrPhotoSearchFragment.newInstance();
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
        return R.id.nav_menu_item_flickr;
    }
}
