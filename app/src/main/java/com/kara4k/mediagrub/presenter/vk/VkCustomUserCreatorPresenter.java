package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.UsersMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VkCustomUserCreatorPresenter extends CustomCreatorPresenter {

    @Inject
    VkApi mVkApi;
    @Inject
    UsersMapper mUsersMapper;

    @Inject
    public VkCustomUserCreatorPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(String input) {
        if (input.startsWith(URL_S)) {
            String[] split = input.split("/");

            if (split.length > 3){
                String name = split[3];
                String[] nameSplit = name.split("\\?");
                return nameSplit[0];
            }
        }
        return input;
    }

    @Override
    protected void requestUserInfo(String id) {
        String token = VkAuthManager.getToken();

        mVkApi.getUsersInfo(id, token)
                .flatMap(mUsersMapper)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected int getService() {
        return CustomUser.VK;
    }

    @Override
    protected int getUserType() {
        return CustomUser.USER;
    }
}
