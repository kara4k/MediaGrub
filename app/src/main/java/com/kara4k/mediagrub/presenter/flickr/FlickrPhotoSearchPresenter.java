package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.GroupPhotoMapper;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FlickrPhotoSearchPresenter extends SearchPresenter {

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    GroupPhotoMapper mGroupPhotoMapper;
    private SystemData mSystemData;

    @Inject
    public FlickrPhotoSearchPresenter(SystemData systemData) {
        mSystemData = systemData;
        mUserItem = mSystemData.createSearchUser(UserItem.FLICKR);
        mAlbumItem = mSystemData.createSearchAlbum();
    }

    @Override
    public void onQuerySubmit(String query) {
        super.onQuerySubmit(query);

        mFlickrApi.searchPhotos(query, 1)
                .flatMap(mGroupPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        mFlickrApi.searchPhotos(mQuery, mGroupPhotoMapper.getPage())
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
