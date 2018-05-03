package com.kara4k.mediagrub.presenter.flickr;


import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.flickr.id.User;
import com.kara4k.mediagrub.model.flickr.id.UserIdResponse;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.flickr.mappers.UserMapper;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FlickrCustomCreatorPresenter extends CustomCreatorPresenter {

    public static final String URL_PATTERN = "https://www.flickr.com/people/%s/";

    @Inject
    FlickrApi mFlickrApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public FlickrCustomCreatorPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(String input) {
        if (input.startsWith(URL_S)) return input;
        return String.format(Locale.ENGLISH, URL_PATTERN, input);
    }

    @Override
    protected void requestUserInfo(String url) {

        mFlickrApi.getUserId(url)
                .map(UserIdResponse::getUser)
                .map(User::getId)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .subscribe(this::showUserInfo, this::onError);
    }

    private void showUserInfo(String id) {
        mFlickrApi.getUserInfo(id)
                .flatMap(mUserMapper)
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
        return CustomUser.USER;
    }
}
