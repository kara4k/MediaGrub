package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.CustomGroupMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VkCustomGroupCreatorPresenter extends CustomCreatorPresenter {

    public static final String PUBLIC = "public";

    @Inject
    VkApi mVkApi;
    @Inject
    CustomGroupMapper mCustomGroupMapper;

    @Inject
    public VkCustomGroupCreatorPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(String input) {
        if (input.startsWith(URL_S)) {
            String[] split = input.split("/");

            if (split.length > 3) {
                String name = split[3];
                String[] nameSplit = name.split("\\?");
                String realName = nameSplit[0];

                if (realName.startsWith(PUBLIC) && realName.length() > PUBLIC.length()) {
                    return realName.substring(PUBLIC.length());
                }

                return nameSplit[0];
            }
        }
        return input;
    }

    @Override
    protected void requestUserInfo(String id) {
        String token = VkAuthManager.getToken();

        mVkApi.getGroupsInfo(id, token)
                .flatMap(mCustomGroupMapper)
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
        return CustomUser.GROUP;
    }
}
