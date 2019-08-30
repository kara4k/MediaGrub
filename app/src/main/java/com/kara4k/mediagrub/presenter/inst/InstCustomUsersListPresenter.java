package com.kara4k.mediagrub.presenter.inst;


import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.base.CustomOneUserOnlyPresenter;
import com.kara4k.mediagrub.presenter.inst.mappers.UserMapper;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class InstCustomUsersListPresenter extends CustomOneUserOnlyPresenter {

    @Inject
    InstApi mInstApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public InstCustomUsersListPresenter(final DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void requestSingleUserInfo(final CustomUser customUser) throws Exception {
        mInstApi.getUserInfo(customUser.getKey())
                .flatMap(mUserMapper)
                .subscribeOn(Schedulers.io())
                .subscribe(getSingleUserObserver());
    }

    @Override
    protected String getKey(final UserItem userItem) {
        return userItem.getAdditionText();
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
