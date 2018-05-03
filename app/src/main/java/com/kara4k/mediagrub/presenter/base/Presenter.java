package com.kara4k.mediagrub.presenter.base;


import io.reactivex.disposables.CompositeDisposable;

public abstract class Presenter {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void onDestroy(){
        mCompositeDisposable.dispose();
    }
}
