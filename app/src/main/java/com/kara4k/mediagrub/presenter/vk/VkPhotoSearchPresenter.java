package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.PhotoMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class VkPhotoSearchPresenter extends SearchPresenter {

    @Inject
    VkApi mVkApi;
    @Inject
    PhotoMapper mPhotoMapper;
    private SystemData mSystemData;

    @Inject
    public VkPhotoSearchPresenter(SystemData systemData) {
        mSystemData = systemData;
        mUserItem = mSystemData.createSearchUser(UserItem.VKONTAKTE);
        mAlbumItem = mSystemData.createSearchAlbum();
    }

    @Override
    public void onQuerySubmit(String query) {
        super.onQuerySubmit(query);
        String token = VkAuthManager.getToken();
        getPhotos(query, token);
    }

    private void getPhotos(String query, String token) {
        mVkApi.searchPhotos(query, token)
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

        mVkApi.searchPhotos(mQuery, offset, token)
                .flatMap(mPhotoMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }
}
