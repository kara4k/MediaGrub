package com.kara4k.mediagrub.view.flickr;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.flickr.FlickrUserPhotosPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListFragment;

import javax.inject.Inject;

public class FlickrUserPhotoListFragment extends MediaListFragment{

    @Inject
    FlickrUserPhotosPresenter mPresenter;

    public static FlickrUserPhotoListFragment newInstance(UserItem userItem, AlbumItem albumItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        args.putSerializable(ALBUM, albumItem);
        FlickrUserPhotoListFragment fragment = new FlickrUserPhotoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected MediaListPresenter getMediaListPresenter() {
        return mPresenter;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrUserPhotosFragment(this);
    }
}
