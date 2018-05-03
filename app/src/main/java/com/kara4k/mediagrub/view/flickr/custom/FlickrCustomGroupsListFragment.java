package com.kara4k.mediagrub.view.flickr.custom;


import android.content.Intent;
import android.os.Bundle;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.presenter.flickr.FlickrCustomGroupsListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.CustomUsersListFragment;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintCustom;
import com.kara4k.mediagrub.view.flickr.FlickrGroupsPhotoListFragment;
import com.kara4k.mediagrub.view.main.UserCreatorActivity;

public class FlickrCustomGroupsListFragment extends CustomUsersListFragment<FlickrCustomGroupsListPresenter> {

    public static FlickrCustomGroupsListFragment newInstance() {
        Bundle args = new Bundle();
        FlickrCustomGroupsListFragment fragment = new FlickrCustomGroupsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrCustomGroupsListFrag(this);
    }

    @Override
    protected Hint getHint() {
        return new HintCustom() {
            @Override
            public String getMessage() {
                return getString(getTemplateResId(), getString(R.string.hint_groups));
            }
        };
    }

    @Override
    public void showUserCreator() {
        Intent intent = UserCreatorActivity.newIntent(
                getContext(), CustomUser.FLICKR, CustomUser.GROUP);
        activityStart(intent);
    }

    @Override
    public void showAlbums(UserItem userItem) {
        addFragment(FlickrGroupsPhotoListFragment.newInstance(userItem, null));
    }
}
