package com.kara4k.mediagrub.view.inst.search;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.presenter.inst.InstPhotoSearchPresenter;
import com.kara4k.mediagrub.view.base.media.SearchFragment;

import javax.inject.Inject;

public class InstPhotoSearchFragment extends SearchFragment {

    @Inject
    InstPhotoSearchPresenter mPresenter;

    public static InstPhotoSearchFragment newInstance() {
        Bundle args = new Bundle();
        InstPhotoSearchFragment fragment = new InstPhotoSearchFragment();
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
                .build().injectInstPhotoSearchFrag(this);
    }
}
