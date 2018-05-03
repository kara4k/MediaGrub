package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.presenter.base.AlbumsPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.PhotoAlbumMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VkPhotoAlbumsPresenter extends AlbumsPresenter {

    public static final String GROUP_PHOTOS_DISABLED = "Access denied: group photos are disabled";

    @Inject
    VkApi mVkApi;
    @Inject
    PhotoAlbumMapper mPhotoAlbumMapper;
    @Inject
    SystemData mSystemData;

    @Inject
    public VkPhotoAlbumsPresenter() {
    }

    @Override
    public void onStart(UserItem userItem) {
        if (userItem == null) {
            getView().showHint();
            return;
        }

        super.onStart(userItem);

        mUserItem = userItem;
        String id = userItem.getId();
        String token = VkAuthManager.getToken();

        getPhotoAlbums(id, token);
    }

    private void getPhotoAlbums(String ownerId, String token) {
        mVkApi.getPhotoAlbums(ownerId, token)
                .flatMap(mPhotoAlbumMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onError(Throwable e) {
        if (e.getMessage().equals(GROUP_PHOTOS_DISABLED)) {
            onSuccess(mSystemData.getSystemAlbums(mUserItem.getId()));
            return;
        }
        super.onError(e);
    }

    @Override
    public void onItemClicked(int position, AlbumItem albumItem) {
        getView().showAlbumContent(mUserItem, albumItem);
    }
}
