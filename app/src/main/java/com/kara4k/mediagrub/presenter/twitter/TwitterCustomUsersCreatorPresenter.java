package com.kara4k.mediagrub.presenter.twitter;


import com.kara4k.mediagrub.api.TwitterApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.twitter.mappers.UsersMapper;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TwitterCustomUsersCreatorPresenter extends CustomCreatorPresenter {

    @Inject
    TwitterApi mTwitterApi;
    @Inject
    UsersMapper mUsersMapper;

    @Inject
    public TwitterCustomUsersCreatorPresenter(final DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(final String input) {
        if (input.startsWith(URL_S)) {
            final String[] split = input.split("/");

            if (split.length > 3) {
                final String name = split[3];
                final String[] nameSplit = name.split("\\?");
                return nameSplit[0];
            }
        }
        return input;
    }

    @Override
    protected void requestUserInfo(final String userName) {
        mTwitterApi.getUsersInfo(userName)
                .flatMap(mUsersMapper)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

    }

    @Override
    protected int getService() {
        return CustomUser.TWITTER;
    }

    @Override
    protected int getUserType() {
        return CustomUser.USER;
    }
}
