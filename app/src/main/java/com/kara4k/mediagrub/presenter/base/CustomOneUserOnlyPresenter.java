package com.kara4k.mediagrub.presenter.base;


import android.support.annotation.NonNull;

import com.kara4k.mediagrub.cache.CustomOneUsersCache;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class CustomOneUserOnlyPresenter extends CustomUsersPresenter {

    private int mUsersCount;
    private final List<UserItem> mUserItems = new ArrayList<>();
    private Observer<UserItem> singleUserObserver;

    private final CustomOneUsersCache customOneUsersCache = CustomOneUsersCache.getInstance();

    public CustomOneUserOnlyPresenter(final DaoSession daoSession) {
        super(daoSession);
        createSingleUserObserver();
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
                .subscribe(this::getSingleUserInfo, this::onError, this::onZeroUsersCount);
    }

    private void getSingleUserInfo(final CustomUser customUser) throws Exception {
        final UserItem userItem = customOneUsersCache.getOrNull(customUser);
        if (userItem != null) {
            Observable.just(userItem).subscribeOn(Schedulers.io()).subscribe(singleUserObserver);
        } else {
            requestSingleUserInfo(customUser);
        }
    }

    private boolean setupTotalCount(final List<CustomUser> customUsers) {
        mUsersCount = customUsers.size();
        return true;
    }

    private void onZeroUsersCount() {
        if (mUsersCount == 0) {
            getView().hideLoading();
            getView().showHint();
        }
    }

    private void createSingleUserObserver() {
        singleUserObserver = new Observer<UserItem>() {
            @Override
            public void onSubscribe(final Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(final UserItem userItem) {
                customOneUsersCache.putIfNotAlreadyExists(userItem);
                mUserItems.add(userItem);
                --mUsersCount;
            }

            @Override
            public void onError(final Throwable e) {
                --mUsersCount;
                this.onComplete();
            }

            @Override
            public void onComplete() {
                if (mUsersCount <= 0) onUsersListReady();
            }
        };
    }

    @NonNull
    protected Observer<UserItem> getSingleUserObserver() {
        return singleUserObserver;
    }

    private void onUsersListReady() {
        Completable.fromAction(() -> onSuccess(mUserItems))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, this::onError);
    }

}
