package com.kara4k.mediagrub.presenter.base;


import android.support.annotation.CallSuper;

import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.SearchViewIF;

import java.util.List;

public abstract class SearchPresenter extends MediaListPresenter<SearchViewIF> {

    protected String mQuery = "";

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        getView().showHint();
        setupTitle();
    }

    @CallSuper
    public void onQuerySubmit(String query) {
        mQuery = query;
        mIsAppend = false;
        mAlbumItem.setTitle(mQuery);
        mAlbumItem.setId(mQuery);
        mAlbumItem.setSize(SystemData.UNDEFINED_SIZE);
        getView().hideHint();
        getView().showLoading();
    }

    @CallSuper
    @Override
    public void onSuccess(List<MediaItem> list) {
        super.onSuccess(list);
        if (list.isEmpty()) getView().showNothingFound();
    }

}
