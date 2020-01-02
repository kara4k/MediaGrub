package com.kara4k.mediagrub.presenter.inst;


import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.presenter.inst.mappers.UserMapper;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstCustomUsersCreatorPresenter extends CustomCreatorPresenter {

    private static final String P = "p";

    @Inject
    InstApi mInstApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public InstCustomUsersCreatorPresenter(final DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(final String input) {
        if (input.startsWith(URL_S)) {
            final String[] split = input.split("/");

            if (split.length > 3) {
                final String name = split[3];

                if (name.equals(P) && split.length > 5) {
                    final String[] takenByName = split[5].split("=");
                    if (takenByName.length > 1) return takenByName[1];
                }

                return name;
            }
        }
        return input;
    }

    @Override
    protected void requestUserInfo(final String username) {
        mInstApi.getUserInfo(username)
                .flatMap(response -> mUserMapper.apply(response, username))
                .singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onError(final Throwable e) {
        super.onError(e);
    }

    @Override
    protected String getDbKey() {
        return mUserItem.getAdditionText();
    }

    @Override
    protected int getService() {
        return CustomUser.INSTAGRAM;
    }

    @Override
    protected int getUserType() {
        return CustomUser.USER;
    }
}
