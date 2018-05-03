package com.kara4k.mediagrub.presenter.inst;


import com.google.gson.Gson;
import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.inst.RequestObject;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.inst.mappers.PhotoMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PhotoListPresenter extends MediaListPresenter<MediaListViewIF> {

    @Inject
    InstApi mInstApi;
    @Inject
    PhotoMapper mPhotoMapper;

    @Inject
    public PhotoListPresenter() {

    }

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        super.onStart(userItem, albumItem);
        createAlbum(userItem);
        setupTitle();

        RequestObject requestObject = new RequestObject(userItem.getId(), null);
        String request = new Gson().toJson(requestObject);

        mInstApi.getPhotos(request)
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        if (mAlbumItem.getTotalSize() == AlbumItem.UNDEFINED) {
            mAlbumItem.setSize(String.valueOf(mPhotoMapper.getAlbumSize()));
        }
        super.onSuccess(list);
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        RequestObject requestObject = new RequestObject(mUserItem.getId(), mPhotoMapper.getLastId());
        String request = new Gson().toJson(requestObject);

        mInstApi.getPhotos(request)
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }
}
