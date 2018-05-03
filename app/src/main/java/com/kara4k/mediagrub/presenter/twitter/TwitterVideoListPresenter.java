package com.kara4k.mediagrub.presenter.twitter;


import com.kara4k.mediagrub.presenter.twitter.mappers.PhotoMapper;
import com.kara4k.mediagrub.presenter.twitter.mappers.VideoMapper;

import javax.inject.Inject;

public class TwitterVideoListPresenter extends TwitterPhotoListPresenter {

    @Inject
    VideoMapper mVideoMapper;

    @Inject
    public TwitterVideoListPresenter() {
    }

    @Override
    protected PhotoMapper getMediaMapper() {
        return mVideoMapper;
    }
}
