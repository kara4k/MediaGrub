package com.kara4k.mediagrub.view.twitter;


import android.os.Bundle;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.twitter.TwitterPhotoListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListFragment;

import javax.inject.Inject;

public class TwitterPhotoListFragment extends MediaListFragment {

    @Inject
    TwitterPhotoListPresenter mPresenter;

    public static TwitterPhotoListFragment newInstance(UserItem userItem, AlbumItem albumItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        args.putSerializable(ALBUM, albumItem);
        TwitterPhotoListFragment fragment = new TwitterPhotoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showAlbumLoadProgress(int progress, int max) {
        String message = getString(R.string.dialog_title_loading);
        showLoadingDialog(null, message, UNDEFINED, UNDEFINED, true);
    }

    @Override
    public void updateAlbumProgress(int progress) {}

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectTwitterPhotoListFrag(this);
    }

    @Override
    protected MediaListPresenter getMediaListPresenter() {
        return mPresenter;
    }


}
