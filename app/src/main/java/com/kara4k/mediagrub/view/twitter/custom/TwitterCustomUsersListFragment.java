package com.kara4k.mediagrub.view.twitter.custom;


import android.content.Intent;
import android.os.Bundle;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.presenter.twitter.TwitterCustomUsersListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.CustomUsersListFragment;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintCustom;
import com.kara4k.mediagrub.view.main.UserCreatorActivity;
import com.kara4k.mediagrub.view.twitter.TwitterMediaViewPagerFragment;

public class TwitterCustomUsersListFragment
        extends CustomUsersListFragment<TwitterCustomUsersListPresenter> {

    public static TwitterCustomUsersListFragment newInstance() {
        Bundle args = new Bundle();
        TwitterCustomUsersListFragment fragment = new TwitterCustomUsersListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectTwitterCustomUsersFragment(this);
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        setTitle(getString(R.string.app_name), getString(R.string.nav_item_twitter));
    }

    @Override
    protected Hint getHint() {
        return new HintCustom() {
            @Override
            public String getMessage() {
                return getString(getTemplateResId(), getString(R.string.hint_blogs));
            }
        };
    }

    @Override
    public void showUserCreator() {
        Intent intent = UserCreatorActivity.newIntent(
                getContext(), CustomUser.TWITTER, CustomUser.USER);
        activityStart(intent);
    }

    @Override
    public void showAlbums(UserItem userItem) {
        addFragment(TwitterMediaViewPagerFragment.newInstance(userItem));
    }
}
