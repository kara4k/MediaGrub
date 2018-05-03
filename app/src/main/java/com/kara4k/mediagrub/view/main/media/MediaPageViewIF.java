package com.kara4k.mediagrub.view.main.media;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.view.base.ViewIF;

public interface MediaPageViewIF extends ViewIF {

    void showPhoto(MediaItem photoItem);

    void toggleActions();

    void showSendTo(String sourceUrl);

    void showVideo(MediaItem mediaItem);

    void startVideo();

    void stopVideo();

    void toggleMediaController();
}
