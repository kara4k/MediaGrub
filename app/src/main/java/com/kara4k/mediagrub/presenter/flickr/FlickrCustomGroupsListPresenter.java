package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.flickr.mappers.GroupMapper;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class FlickrCustomGroupsListPresenter extends FlickrCustomUsersListPresenter {

    @Inject
    GroupMapper mGroupMapper;

    @Inject
    public FlickrCustomGroupsListPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void requestSingleUserInfo(CustomUser customUser) throws Exception {
        mFlickrApi.getGroupInfo(customUser.getKey())
                .flatMap(mGroupMapper)
                .subscribeOn(Schedulers.io())
                .subscribe(getSingleUserObserver());
    }

    @Override
    protected int getUserType() {
        return CustomUser.GROUP;
    }
}
