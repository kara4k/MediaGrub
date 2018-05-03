package com.kara4k.mediagrub.view.vk.custom;


import android.content.Intent;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.presenter.vk.VkCustomGroupsPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.CustomUsersListFragment;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintCustom;
import com.kara4k.mediagrub.view.main.UserCreatorActivity;
import com.kara4k.mediagrub.view.vk.VkPhotoAlbumsFragment;

public class VkCustomGroupsListFragment extends CustomUsersListFragment<VkCustomGroupsPresenter> {

    public static VkCustomGroupsListFragment newInstance() {
        return new VkCustomGroupsListFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkCustomGroupsFrag(this);
    }

    @Override
    protected Hint getHint() {
        return new HintCustom(){
            @Override
            public String getMessage() {
                return getString(getTemplateResId(), getString(R.string.hint_groups));
            }
        };
    }

    @Override
    public void showAlbums(UserItem userItem) {
        addFragment(VkPhotoAlbumsFragment.newInstance(userItem));
    }

    @Override
    public void showUserCreator() {
        Intent intent = UserCreatorActivity.newIntent(
                getContext(), CustomUser.VK, CustomUser.GROUP);
        activityStart(intent);
    }
}
