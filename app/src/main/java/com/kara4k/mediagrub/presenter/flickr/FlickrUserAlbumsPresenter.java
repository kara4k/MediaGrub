package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.presenter.base.AlbumsPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.UserAlbumsMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FlickrUserAlbumsPresenter extends AlbumsPresenter {

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    UserAlbumsMapper mUserAlbumsMapper;

    @Inject
    public FlickrUserAlbumsPresenter() {
    }

    @Override
    public void onStart(UserItem userItem) {
        super.onStart(userItem);
        mUserItem = userItem;

        mFlickrApi.getUserAlbums(mUserItem.getId())
                .flatMap(mUserAlbumsMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onItemClicked(int position, AlbumItem albumItem) {
        getView().showAlbumContent(mUserItem, albumItem);
    }
}
