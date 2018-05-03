package com.kara4k.mediagrub.presenter.twitter;


import com.kara4k.mediagrub.api.TwitterApi;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.twitter.mappers.PhotoMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TwitterPhotoListPresenter extends MediaListPresenter<MediaListViewIF> {

    @Inject
    TwitterApi mTwitterApi;
    @Inject
    PhotoMapper mPhotoMapper;

    @Inject
    public TwitterPhotoListPresenter() {
    }

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        super.onStart(userItem, albumItem);
        createAlbum(userItem);
        setupTitle();

        mTwitterApi.getUserTweets(userItem.getId())
                .flatMap(getMediaMapper())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    protected PhotoMapper getMediaMapper() {
        return mPhotoMapper;
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        super.onSuccess(list);
        setupOffsets();

        if (list.isEmpty() && mHasMore) {
            getView().showLoading();
            loadMore(list.size(), this::onSuccess);
        }
    }

    private void setupOffsets() {
        if (mHasMore = getMediaMapper().isHasMore()) {
            mAlbumItem.setSize(String.valueOf(getAllItems().size() + 1));
        } else {
            mAlbumItem.setSize(String.valueOf(getAllItems().size()));
        }
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        mTwitterApi.getUserTweets(mUserItem.getId(), getMediaMapper().getMaxId())
                .flatMap(getMediaMapper())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }
}
