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
    public InstCustomUsersCreatorPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    protected String convertInput(String input) {
        if (input.startsWith(URL_S)) {
            String[] split = input.split("/");

            if (split.length > 3) {
                String name = split[3];

                if (name.equals(P) && split.length > 5) {
                    String[] takenByName = split[5].split("=");
                    if (takenByName.length > 1) return takenByName[1];
                }
                return name;
            }
        }
        return input;
    }

    @Override
    protected void requestUserInfo(String username) {
        mInstApi.getUserInfo(username)
                .flatMap(mUserMapper)
                .singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onError(Throwable e) {
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
