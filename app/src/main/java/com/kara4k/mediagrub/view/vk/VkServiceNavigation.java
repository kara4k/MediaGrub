package com.kara4k.mediagrub.view.vk;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.ServiceNavigation;
import com.kara4k.mediagrub.view.main.AuthActivity;
import com.kara4k.mediagrub.view.vk.custom.VkCustomUsersViewPagerFragment;
import com.kara4k.mediagrub.view.vk.friends.VkFriendsViewPagerFragment;
import com.kara4k.mediagrub.view.vk.own.VkOwnAlbumsFragment;
import com.kara4k.mediagrub.view.vk.search.VkSearchFragment;

public class VkServiceNavigation implements ServiceNavigation {

    private static VkServiceNavigation sVkServiceNavigation;

    public static VkServiceNavigation getInstance() {
        if (sVkServiceNavigation == null) {
            sVkServiceNavigation = new VkServiceNavigation();
        }
        return sVkServiceNavigation;
    }

    private VkServiceNavigation() {
    }

    @Override
    public Fragment getMainFragment() {
        return VkFriendsViewPagerFragment.newInstance();
    }

    @Override
    public void onUpdateMenu(Menu menu) {
        menu.findItem(R.id.service_item_auth).setVisible(true);
        menu.findItem(R.id.service_item_friends).setVisible(true);
        menu.findItem(R.id.service_item_custom_friends).setVisible(true);
        menu.findItem(R.id.service_item_search).setVisible(true);
        menu.findItem(R.id.service_item_own).setVisible(true);
    }

    @Override
    public Intent getAuthIntent(Context context) {
        return AuthActivity.newIntent(context, AuthActivity.VK);
    }

    @Override
    public Fragment getFriends() {
        return VkFriendsViewPagerFragment.newInstance();
    }

    @Override
    public Fragment getCustomFriends() {
        return VkCustomUsersViewPagerFragment.newInstance();
    }

    @Override
    public Fragment getSearch() {
        return VkSearchFragment.newInstance();
    }

    @Override
    public Fragment getOwn() {
        UserItem loggedUser = VkAuthManager.getLoggedUser();
        return VkOwnAlbumsFragment.newInstance(loggedUser);
    }

    @Override
    public int getToolbarVisibility() {
        return View.VISIBLE;
    }

    @Override
    public int getMenuItemId() {
        return R.id.nav_menu_item_vk;
    }
}
