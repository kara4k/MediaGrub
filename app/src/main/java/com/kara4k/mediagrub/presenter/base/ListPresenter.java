package com.kara4k.mediagrub.presenter.base;


import android.support.annotation.CallSuper;
import android.util.Log;

import com.kara4k.mediagrub.other.PreferenceManager;
import com.kara4k.mediagrub.view.base.ListViewIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class ListPresenter<T, V extends ListViewIF<T>> extends Presenter
        implements SingleObserver<List<T>> {

    public static final String EMPTY = "";
    public static final int REQUEST_SD_WRITE = 1;

    @Inject
    protected V mListView;
    @Inject
    protected PreferenceManager mPrefs;
    private List<T> mAllItems;
    private List<T> mFilteredItems;
    protected ListSelector<T> mSelector;

    protected boolean mIsAppend;
    protected boolean mHasMore;
    protected int mOffset;


    public ListPresenter() {
        mSelector = new ListSelector<>();
    }

    abstract public void onItemClicked(int position, T t);

    abstract protected boolean searchFilter(T t, String text);

    public void onSearch(String text) {
        if (mAllItems == null) return;

        Observable.fromIterable(mAllItems)
                .filter(t -> searchFilter(t, text))
                .toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSearchSuccess, this::onError);
    }

    private void onSearchSuccess(List<T> items) {
        mFilteredItems = items;
        mListView.setItems(items);
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onSuccess(List<T> list) {
        if (mIsAppend) {
            mAllItems.addAll(list);
        } else {
            mAllItems = list;
        }
        mListView.setItems(mAllItems);
        mFilteredItems = null;
    }

    @Override
    public void onError(Throwable e) {
        getView().hideLoading();
        e.printStackTrace();
        mListView.showError(e.getMessage());
    }

    protected V getView() {
        return mListView;
    }

    protected List<T> getAllItems() {
        return mAllItems;
    }

    public void onItemClicked(T t, int position) {
        if (mSelector.isActionMode()) {
            mSelector.toggleSelection(position);
        } else {
            onItemClicked(position, t);
        }
    }

    public void onScrollEnd() {
        if (mHasMore) loadMore(mOffset);
    }

    protected void setupOffset(int totalSize) {
        mOffset = mAllItems.size();
        mHasMore = mOffset < totalSize;
    }

    @CallSuper
    protected void loadMore(int offset) {
        mIsAppend = true;
    }

    public void startActionMode(int position) {
        if (mSelector.isActionMode()) {
            mSelector.finishActionMode();
            return;
        }

        List<T> itemsList = mFilteredItems == null ? mAllItems : mFilteredItems;
        mSelector.setItems(itemsList);
        mSelector.startSelection(mListView, position);
    }

    public void onActionModeDestroy() {
        mSelector.resetSelections();
    }

    public void selectAll() {
        mSelector.selectAll();
    }

    public void onDeleteSelected() {
        getView().onDeleteSelected();
    }

    public void onDeleteSelectedConfirm() {
    }

    public void checkStoragePermission() {
        getView().checkWriteSdPermissions(REQUEST_SD_WRITE);
    }

    public void onStoragePermissionGranted(int requestCode) {
    }

    public void onStoragePermissionDenied(int requestCode) {
        getView().showError("Storage permission denied");
    }
}
