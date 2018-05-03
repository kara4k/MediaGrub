package com.kara4k.mediagrub.presenter.main;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.Presenter;
import com.kara4k.mediagrub.view.main.media.MediaPageViewIF;

import javax.inject.Inject;

public class MediaPagePresenter extends Presenter {

    @Inject
    MediaPageViewIF mView;
    private MediaItem mMediaItem;

    @Inject
    public MediaPagePresenter() {
    }

    public void onPhotoClicked() {
        mView.toggleActions();
    }

    public void onStart(MediaItem mediaItem) {
        if (mediaItem == null) return;

        mMediaItem = mediaItem;

        if (mediaItem.getType() == MediaItem.PHOTO) {
            mView.showPhoto(mediaItem);
        } else {
            mView.showVideo(mediaItem);
        }
    }

    public void onShareClicked() {
        mView.showSendTo(mMediaItem.getSourceUrl());
    }

    public void onVisible(boolean isVisibleToUser) {
        if (mMediaItem.getType() == MediaItem.VIDEO) {

            if (isVisibleToUser) {
                mView.startVideo();
            } else {
                mView.stopVideo();
            }
        }
    }

    public void onVideoClicked() {
        mView.toggleActions();
        mView.toggleMediaController();
    }
}
