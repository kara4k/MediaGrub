package com.kara4k.mediagrub.view.base;


import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.presenter.base.UsersListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.BaseAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.UserAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

public abstract class UsersListFragment<T extends UsersListPresenter>
        extends BaseListFragment<UserItem, UserAdapter.UserHolder>
        implements UsersViewIF {

    @Inject
    T mPresenter;

    @Override
    protected BaseAdapter<UserItem, UserAdapter.UserHolder> getAdapter() {
        return new UserAdapter(getListPresenter());
    }

    @Override
    protected ListPresenter<UserItem, UsersViewIF> getListPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void showUserCreator() {

    }

    @Override
    public void showUserSearch() {

    }

    protected T getPresenter() {
        return mPresenter;
    }

}
