package com.kara4k.mediagrub.presenter.base;


import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.AuthViewIF;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

abstract public class AuthPresenter extends Presenter implements MaybeObserver<UserItem> {

    @Inject
    AuthViewIF mView;

    public AuthPresenter() {
    }

    abstract protected boolean isLoggedIn();

    abstract protected void showUserInfo();

    public void onStart() {
        if (isLoggedIn()) {
            showUserInfo();
        } else {
            getView().showLogInBtn();
        }
    }

    public void onAuthClicked() {}

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(UserItem userItem) {
        mView.showUserDetails(userItem);
        getView().showLogOutBtn();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mView.showError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    protected AuthViewIF getView() {
        return mView;
    }
}
