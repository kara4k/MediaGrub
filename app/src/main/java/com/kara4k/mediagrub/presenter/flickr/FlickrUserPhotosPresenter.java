package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.UserPhotosMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FlickrUserPhotosPresenter extends MediaListPresenter<MediaListViewIF> {

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    UserPhotosMapper mUserPhotosMapper;

    @Inject
    public FlickrUserPhotosPresenter() {
    }

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        super.onStart(userItem, albumItem);

        mFlickrApi.getAlbumPhotos(userItem.getId(), albumItem.getId())
                .flatMap(mUserPhotosMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        mFlickrApi.getAlbumPhotos(mUserItem.getId(), mAlbumItem.getId(), mUserPhotosMapper.getPage())
                .flatMap(mUserPhotosMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        mAlbumItem.setSize(String.valueOf(mUserPhotosMapper.getPages()));
        super.onSuccess(list);
    }

    @Override
    protected void setupOffset(int totalSize) {
        mOffset = mUserPhotosMapper.getPage();
        mHasMore = mOffset <= totalSize;
    }
}
