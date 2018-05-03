package com.kara4k.mediagrub.presenter.tumblr;


import com.kara4k.mediagrub.api.TumblrApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.tumblr.mappers.UserMapper;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TumblrCustomUsersCreatorPresenter extends CustomCreatorPresenter {

    private static final String URL = "http:/";

    @Inject
    TumblrApi mTumblrApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public TumblrCustomUsersCreatorPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(String input) {
        if (input.startsWith(URL)) {
            String substring = input.substring(URL.length());
            input = String.format(Locale.ENGLISH, "%s%s", URL_S, substring);
        }

        if (input.startsWith(URL_S)) {
            String[] split = input.split("/");

            if (split.length > 2) {
                input = split[2];
            }
        }

        String[] split = input.split("\\.");
        return split[0];
    }

    @Override
    protected void requestUserInfo(String blogName) {
        mTumblrApi.getUserInfo(blogName)
                .flatMap(mUserMapper)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
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
