package com.kara4k.mediagrub.view.twitter.custom;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.twitter.TwitterCustomUsersCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class TwitterCustomUsersCreatorFragment
        extends CustomCreatorFragment<TwitterCustomUsersCreatorPresenter> {

    public static TwitterCustomUsersCreatorFragment newInstance() {
        Bundle args = new Bundle();
        TwitterCustomUsersCreatorFragment fragment = new TwitterCustomUsersCreatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectTwitterCustomUsersCreatorFragment(this);
    }
}
