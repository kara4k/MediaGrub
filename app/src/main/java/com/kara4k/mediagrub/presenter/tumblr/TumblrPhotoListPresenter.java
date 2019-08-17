package com.kara4k.mediagrub.presenter.tumblr;


import com.kara4k.mediagrub.api.TumblrApi;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.tumblr.photo.PhotoResponse;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.tumblr.mappers.PhotoMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TumblrPhotoListPresenter extends MediaListPresenter<MediaListViewIF> {

    @Inject
    TumblrApi mTumblrApi;
    @Inject
    PhotoMapper mPhotoMapper;

    @Inject
    public TumblrPhotoListPresenter() {
    }

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        super.onStart(userItem, albumItem);
        createAlbum(userItem);
        setupTitle();

        mTumblrApi.getPhotos(userItem.getId())
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        mAlbumItem.setSize(String.valueOf(mPhotoMapper.getTotalPosts()));
        super.onSuccess(list);
    }

    @Override
    protected void setupOffset(int totalSize) {
        mOffset = mPhotoMapper.getOffset();
        mHasMore = mOffset < mPhotoMapper.getTotalPosts();
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        mTumblrApi.getPhotos(mUserItem.getId(), mPhotoMapper.getOffset())
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }
}
