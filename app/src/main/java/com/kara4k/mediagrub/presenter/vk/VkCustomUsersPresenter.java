package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomUsersPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.UsersMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VkCustomUsersPresenter extends CustomUsersPresenter {

    @Inject
    VkApi mVkApi;
    @Inject
    UsersMapper mUsersMapper;

    @Inject
    public VkCustomUsersPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected int getService() {
        return CustomUser.VK;
    }

    protected int getUserType() {
        return CustomUser.USER;
    }

    @Override
    public void onStart() {
        Single.fromCallable(this::getCustomIds)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getUsersInfo, this::onError);
    }

    protected void getUsersInfo(String ids) throws Exception {
        if (ids.equals(EMPTY)) {
            onSuccess(new ArrayList<>());
            return;
        }

        getView().showLoading();
        mVkApi.getUsersInfo(ids, VkAuthManager.getToken())
                .flatMap(mUsersMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

}
