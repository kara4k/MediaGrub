package com.kara4k.mediagrub.presenter.inst;

import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.inst.users.User;
import com.kara4k.mediagrub.model.inst.users.UsersResponse;
import com.kara4k.mediagrub.presenter.base.UserSearchPresenter;
import com.kara4k.mediagrub.presenter.inst.mappers.UserMapper;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.UserSearchIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstUserSearchPresenter extends UserSearchPresenter {

    @Inject
    InstApi mInstApi;
    @Inject
    UserMapper mUserMapper;

    @Inject
    public InstUserSearchPresenter(final UserSearchIF view, final DaoSession daoSession) {
        super(view, daoSession);
    }

    @Override
    protected String getDbKey(final UserItem userItem) {
        return userItem.getAdditionText();
    }

    @Override
    protected void performUserSearch(final String text) {
        mInstApi.getUserInfo(text)
                .map(UsersResponse::getUsers)
                .flatMapIterable(users -> users)
                .map(User::getUser)
                .map(mUserMapper::map)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
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
