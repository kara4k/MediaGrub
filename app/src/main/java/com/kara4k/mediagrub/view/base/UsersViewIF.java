package com.kara4k.mediagrub.view.base;


import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

public interface UsersViewIF extends ListViewIF<UserItem> {

    void showAlbums(UserItem userItem);

    void showUserCreator();

    void showUserSearch();
}
