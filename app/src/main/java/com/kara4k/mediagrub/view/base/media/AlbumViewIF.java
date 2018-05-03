package com.kara4k.mediagrub.view.base.media;


import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.ListViewIF;

public interface AlbumViewIF extends ListViewIF<AlbumItem> {

    void showAlbumContent(UserItem userItem, AlbumItem albumItem);
}
