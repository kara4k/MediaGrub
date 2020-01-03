package com.kara4k.mediagrub.view.base;


import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

public interface CustomCreatorIF extends ViewIF {

    void showUserDetails(UserItem userItem);

    void showUserAdded();

    void showUserAddedButPrivate();

    void showUserAlreadyExists();

    void hideUserInfo();

    void hideKeyboard();
}
