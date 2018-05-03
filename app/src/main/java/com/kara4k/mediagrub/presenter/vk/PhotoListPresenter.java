package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.PhotoMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;
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
    VkApi mVkApi;
    @Inject
    PhotoMapper mPhotoMapper;

    @Inject
    public PhotoListPresenter() {
    }

    @Override
    public void onStart(UserItem userItem, AlbumItem albumItem) {
        super.onStart(userItem, albumItem);
        String token = VkAuthManager.getToken();

        getPhotos(mUserItem.getId(), mAlbumItem.getId(), token);
    }

    private void getPhotos(String ownerId, String albumId, String token) {
        mVkApi.getPhotos(ownerId, albumId, token)
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        if (mAlbumItem.getTotalSize() == AlbumItem.UNDEFINED) {
            mAlbumItem.setSize(String.valueOf(mPhotoMapper.getTotal()));
        }
        super.onSuccess(list);
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        String token = VkAuthManager.getToken();
        getMorePhotos(mUserItem.getId(), mAlbumItem.getId(), offset, token, onPartLoaded);
    }

    private void getMorePhotos(String ownerId, String albumId, int offset, String token,
                               Consumer<List<MediaItem>> onPartLoaded) {
        mVkApi.getPhotos(ownerId, albumId, offset, token)
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }
}
