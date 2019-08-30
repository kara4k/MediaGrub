package com.kara4k.mediagrub.presenter.base;


import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.UsersViewIF;

import java.util.List;

public abstract class UsersListPresenter extends ListPresenter<UserItem, UsersViewIF> {

    public void onStart(){
        getView().showLoading();
    }

    @Override
    public void onSuccess(final List<UserItem> list) {
        getView().hideLoading();
        super.onSuccess(list);
    }

    @Override
    public void startActionMode(final int position) {
        if (isActionModeEnabled()) super.startActionMode(position);
    }

    protected boolean isActionModeEnabled(){
        return false;
    }

    @Override
    protected boolean searchFilter(final UserItem userItem, final String text) {
        final String mainText = userItem.getMainText() == null
                ? EMPTY : userItem.getMainText().toLowerCase();
        final String additionText = userItem.getAdditionText() == null
                ? EMPTY : userItem.getAdditionText().toLowerCase();
        final String query = text.toLowerCase();

        return mainText.contains(query) || additionText.contains(query);
    }
}
