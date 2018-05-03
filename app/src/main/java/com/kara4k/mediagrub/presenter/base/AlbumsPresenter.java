package com.kara4k.mediagrub.presenter.base;


import android.support.annotation.CallSuper;

import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.AlbumViewIF;

import java.util.List;

public abstract class AlbumsPresenter extends ListPresenter<AlbumItem, AlbumViewIF> {

    protected UserItem mUserItem;

    public void onStart(UserItem userItem){
        setupTitle(userItem);
        getView().showLoading();
    }

    private void setupTitle(UserItem userItem) {
        if (userItem != null) {
            getView().setToolbarTitle(userItem.getMainText(), userItem.getAdditionText());
        }
    }

    @CallSuper
    @Override
    public void onSuccess(List<AlbumItem> list) {
        getView().hideLoading();
        super.onSuccess(list);
    }

    @Override
    protected boolean searchFilter(AlbumItem albumItem, String text) {
        String title = albumItem.getTitle() == null ? EMPTY : albumItem.getTitle().toLowerCase();
        String description = albumItem.getDescription() == null
                ? EMPTY : albumItem.getDescription().toLowerCase();
        String query = text.toLowerCase();

        return title.contains(query) || description.contains(query);
    }
}
