package com.kara4k.mediagrub.view.tumblr.custom;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.tumblr.TumblrCustomUsersCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class TumblrCustomUsersCreatorFragment
        extends CustomCreatorFragment<TumblrCustomUsersCreatorPresenter> {

    public static TumblrCustomUsersCreatorFragment newInstance() {
        Bundle args = new Bundle();
        TumblrCustomUsersCreatorFragment fragment = new TumblrCustomUsersCreatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectTumblrCustomUsersCreatorFrag(this);
    }
}
