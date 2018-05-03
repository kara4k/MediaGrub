package com.kara4k.mediagrub.view.flickr;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.flickr.FlickrGroupsPhotosPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListFragment;

import javax.inject.Inject;

public class FlickrGroupsPhotoListFragment extends MediaListFragment {

    @Inject
    FlickrGroupsPhotosPresenter mPresenter;

    public static FlickrGroupsPhotoListFragment newInstance(UserItem userItem, AlbumItem albumItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        args.putSerializable(ALBUM, albumItem);
        FlickrGroupsPhotoListFragment fragment = new FlickrGroupsPhotoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrGroupPhotosFragment(this);
    }

    @Override
    protected MediaListPresenter getMediaListPresenter() {
        return mPresenter;
    }
}
