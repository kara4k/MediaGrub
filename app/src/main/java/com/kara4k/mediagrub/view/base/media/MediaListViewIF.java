package com.kara4k.mediagrub.view.base.media;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.view.base.ListViewIF;

public interface MediaListViewIF extends ListViewIF<MediaItem> {

    void setLayoutMenuItem(int layoutType);

    void setLayout(int layoutType);

    void startDownload(long taskId);

    void checkWriteSdPermissions(int requestCode);

    void showDetails(int position);

    void showTaskIsCreating();

    void showAlbumLoadProgress(int progress, int max);

    void updateAlbumProgress(int progress);

    void setCurrentPosition(int position);
}
