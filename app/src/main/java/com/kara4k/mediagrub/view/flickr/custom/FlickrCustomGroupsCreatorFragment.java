package com.kara4k.mediagrub.view.flickr.custom;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.flickr.FlickrCustomGroupCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class FlickrCustomGroupsCreatorFragment
        extends CustomCreatorFragment<FlickrCustomGroupCreatorPresenter> {

    public static FlickrCustomGroupsCreatorFragment newInstance() {
        Bundle args = new Bundle();
        FlickrCustomGroupsCreatorFragment fragment = new FlickrCustomGroupsCreatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrCustomGroupsCreatorFrag(this);
    }
}
