package com.kara4k.mediagrub.view.base;


import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

public interface AuthViewIF extends ViewIF {

    void showLogInBtn();

    void showLogOutBtn();

    void showUserDetails(UserItem userItem);

    void hideUserInfo();

    void showAuthDialog();
}
