package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.presenter.base.UsersListPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.FriendsMapper;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FriendsPresenter extends UsersListPresenter {

    @Inject
    VkApi mVkApi;
    @Inject
    FriendsMapper mFriendsMapper;

    @Inject
    public FriendsPresenter() {
    }

    @Override
    public void onStart() {
        if (VKSdk.isLoggedIn()) {
            super.onStart();
            String token = VKAccessToken.currentToken().accessToken;
            getFriends(token);
        } else {
            getView().showHint();
            getView().setItems(new ArrayList<>());
        }
    }

    private void getFriends(String token) {
        mVkApi.getFriends(token).flatMap(mFriendsMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onItemClicked(int position, UserItem userItem) {
        getView().showAlbums(userItem);
    }
}
