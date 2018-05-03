package com.kara4k.mediagrub.view.flickr.custom;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.flickr.FlickrCustomCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class FlickrCustomUsersCreatorFragment
        extends CustomCreatorFragment<FlickrCustomCreatorPresenter>{

    public static FlickrCustomUsersCreatorFragment newInstance() {
        Bundle args = new Bundle();
        FlickrCustomUsersCreatorFragment fragment = new FlickrCustomUsersCreatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrCustomUsersCreatorFrag(this);
    }
}
