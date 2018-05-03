package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.presenter.vk.mappers.UsersMapper;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VkAuthPresenter extends com.kara4k.mediagrub.presenter.base.AuthPresenter {

    @Inject
    VkApi mVkApi;
    @Inject
    UsersMapper mUsersMapper;

    @Inject
    public VkAuthPresenter() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void showUserInfo() {
        String token = VKAccessToken.currentToken().accessToken;
        String ownerId = VKAccessToken.currentToken().userId;
        mVkApi.getUsersInfo(ownerId, token)
                .flatMap(mUsersMapper)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onAuthClicked() {
        if (isLoggedIn()) {
            VKSdk.logout();
            getView().hideUserInfo();
            getView().showLogInBtn();
        } else {
            getView().showAuthDialog();
        }
    }

    @Override
    protected boolean isLoggedIn() {
       return VKSdk.isLoggedIn();
    }

}
