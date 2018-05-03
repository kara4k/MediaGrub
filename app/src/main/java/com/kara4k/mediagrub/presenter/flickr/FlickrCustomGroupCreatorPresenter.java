package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.flickr.groupid.Group;
import com.kara4k.mediagrub.model.flickr.groupid.GroupIdResponse;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.GroupMapper;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FlickrCustomGroupCreatorPresenter extends CustomCreatorPresenter{

    public static final String URL_PATTERN = "https://www.flickr.com/groups/%s/";

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    GroupMapper mGroupMapper;


    @Inject
    public FlickrCustomGroupCreatorPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(String input) {
        if (input.startsWith(URL_S)) return input;
        return String.format(Locale.ENGLISH, URL_PATTERN, input);
    }

    @Override
    protected void requestUserInfo(String url) {

        mFlickrApi.getGroupId(url)
                .map(GroupIdResponse::getGroup)
                .map(Group::getId)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .subscribe(this::showGroupInfo, this::onError, this::onComplete);
    }

    private void showGroupInfo(String id) {
        mFlickrApi.getGroupInfo(id)
                .flatMap(mGroupMapper)
                .singleElement()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected int getService() {
        return CustomUser.FLICKR;
    }

    @Override
    protected int getUserType() {
        return CustomUser.GROUP;
    }
}
