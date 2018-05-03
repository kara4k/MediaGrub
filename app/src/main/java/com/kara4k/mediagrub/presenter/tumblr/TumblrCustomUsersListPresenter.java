package com.kara4k.mediagrub.presenter.tumblr;


import com.kara4k.mediagrub.api.TumblrApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomOneUserOnlyPresenter;
import com.kara4k.mediagrub.presenter.tumblr.mappers.UserMapper;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class TumblrCustomUsersListPresenter extends CustomOneUserOnlyPresenter {

    @Inject
    TumblrApi mTumblrApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public TumblrCustomUsersListPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void requestSingleUserInfo(CustomUser customUser) throws Exception {
        mTumblrApi.getUserInfo(customUser.getKey())
                .flatMap(mUserMapper)
                .subscribeOn(Schedulers.io())
                .subscribe(getSingleUserObserver());
    }

    @Override
    protected int getService() {
        return CustomUser.TUMBLR;
    }

    @Override
    protected int getUserType() {
        return CustomUser.USER;
    }
}
