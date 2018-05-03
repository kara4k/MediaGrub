package com.kara4k.mediagrub.view.flickr;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.AlbumsPresenter;
import com.kara4k.mediagrub.presenter.flickr.FlickrUserAlbumsPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.AlbumsFragment;

import javax.inject.Inject;

public class FlickrAlbumsListFragment extends AlbumsFragment {

    @Inject
    FlickrUserAlbumsPresenter mPresenter;


    public static FlickrAlbumsListFragment newInstance(UserItem userItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        FlickrAlbumsListFragment fragment = new FlickrAlbumsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectFlickrAlbumsListFragment(this);
    }

    @Override
    public void showAlbumContent(UserItem userItem, AlbumItem albumItem) {
        addFragment(FlickrUserPhotoListFragment.newInstance(userItem, albumItem));
    }

    @Override
    protected AlbumsPresenter getAlbumsPresenter() {
        return mPresenter;
    }
}
