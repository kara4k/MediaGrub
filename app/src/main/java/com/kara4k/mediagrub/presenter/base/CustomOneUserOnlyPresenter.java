package com.kara4k.mediagrub.presenter.base;


import android.support.annotation.NonNull;

import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class CustomOneUserOnlyPresenter extends CustomUsersPresenter {

    private int mUsersCount;
    private List<UserItem> mUserItems = new ArrayList<>();

    public CustomOneUserOnlyPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    abstract public void requestSingleUserInfo(CustomUser customUser) throws Exception;

    @Override
    public void onStart() {
        getView().showLoading();
        mUserItems.clear();

        Observable.fromCallable(this::getUsers)
                .filter(this::setupTotalCount)
                .flatMapIterable(customUsers -> customUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::requestSingleUserInfo, this::onError, this::onZeroUsersCount);
    }

    private boolean setupTotalCount(List<CustomUser> customUsers) {
        mUsersCount = customUsers.size();
        return true;
    }

    private void onZeroUsersCount() {
        if (mUsersCount == 0) {
            getView().hideLoading();
            getView().showHint();
        }
    }

    @NonNull
    protected Observer<UserItem> getSingleUserObserver() {
        return new Observer<UserItem>() {
            @Override
            public void onSubscribe(Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(UserItem userItem) {
                mUserItems.add(userItem);
                --mUsersCount;
            }

            @Override
            public void onError(Throwable e) {
                --mUsersCount;
            }

            @Override
            public void onComplete() {
                if (mUsersCount == 0) onUsersListReady();
            }
        };
    }

    private void onUsersListReady() {
        Completable.fromAction(() -> onSuccess(mUserItems))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, this::onError);
    }

}
