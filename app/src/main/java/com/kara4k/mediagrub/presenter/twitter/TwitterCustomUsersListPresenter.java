package com.kara4k.mediagrub.presenter.twitter;


import com.kara4k.mediagrub.api.TwitterApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomUsersPresenter;
import com.kara4k.mediagrub.presenter.twitter.mappers.UsersMapper;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TwitterCustomUsersListPresenter extends CustomUsersPresenter {

    @Inject
    TwitterApi mTwitterApi;
    @Inject
    UsersMapper mUsersMapper;

    @Inject
    public TwitterCustomUsersListPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onStart() {
        super.onStart();

        Single.fromCallable(this::getCustomIds)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getUsersInfo, this::onError);
    }

    private void getUsersInfo(String userNames) {
        if (userNames.equals(EMPTY)) {
            onSuccess(new ArrayList<>());
            return;
        }

        getView().showLoading();
        mTwitterApi.getUsersInfo(userNames)
                .flatMap(mUsersMapper)
                .toList()
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
