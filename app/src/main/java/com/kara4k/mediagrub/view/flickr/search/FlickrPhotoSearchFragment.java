package com.kara4k.mediagrub.view.flickr.search;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.presenter.flickr.FlickrPhotoSearchPresenter;
import com.kara4k.mediagrub.view.base.media.SearchFragment;

import javax.inject.Inject;

public class FlickrPhotoSearchFragment extends SearchFragment {

    @Inject
    FlickrPhotoSearchPresenter mPresenter;

    public static FlickrPhotoSearchFragment newInstance() {
        Bundle args = new Bundle();
        FlickrPhotoSearchFragment fragment = new FlickrPhotoSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SearchPresenter getSearchPresenter() {
        return mPresenter;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrPhotoSearchFragment(this);
    }
}
