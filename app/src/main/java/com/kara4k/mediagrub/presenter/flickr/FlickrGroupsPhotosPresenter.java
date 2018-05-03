package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.GroupPhotoMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FlickrGroupsPhotosPresenter extends MediaListPresenter<MediaListViewIF> {

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    GroupPhotoMapper mGroupPhotoMapper;

    @Inject
    public FlickrGroupsPhotosPresenter() {
    }

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        super.onStart(userItem, albumItem);
        createAlbum(userItem);
        setupTitle();

        mFlickrApi.getGroupPhotos(mUserItem.getId(), 1)
                .flatMap(mGroupPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        mFlickrApi.getGroupPhotos(mUserItem.getId(), mGroupPhotoMapper.getPage())
                .flatMap(mGroupPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        mAlbumItem.setSize(String.valueOf(mGroupPhotoMapper.getPages()));
        super.onSuccess(list);
    }

    @Override
    protected void setupOffset(int totalSize) {
        mOffset = mGroupPhotoMapper.getPage();
        mHasMore = mOffset <= totalSize;
    }
}
