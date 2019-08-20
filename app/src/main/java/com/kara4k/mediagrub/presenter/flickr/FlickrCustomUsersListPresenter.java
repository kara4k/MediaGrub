package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomOneUserOnlyPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.UserMapper;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class FlickrCustomUsersListPresenter extends CustomOneUserOnlyPresenter {

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public FlickrCustomUsersListPresenter(final DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void requestSingleUserInfo(final CustomUser customUser) throws Exception {
            mFlickrApi.getUserInfo(customUser.getKey())
                    .flatMap(mUserMapper)
                    .subscribeOn(Schedulers.io())
                    .subscribe(getSingleUserObserver());
    }

    @Override
    protected int getService() {
        return CustomUser.FLICKR;
    }

    @Override
    protected int getUserType() {
        return CustomUser.USER;
    }
}
