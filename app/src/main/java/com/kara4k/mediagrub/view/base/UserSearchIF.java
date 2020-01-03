package com.kara4k.mediagrub.view.base;

import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.List;

public interface UserSearchIF extends ViewIF {

    void showFoundUsers(List<UserItem> userItems);

    void showUserAlreadyExists();

    void showUserAdded();

    void hideKeyboard();

    void showUserAddedButPrivate();

    void showProgress();

    void hideProgress();
}
