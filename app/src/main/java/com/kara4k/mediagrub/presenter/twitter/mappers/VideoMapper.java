package com.kara4k.mediagrub.presenter.twitter.mappers;


import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.twitter.media.Medium__;

import javax.inject.Inject;

public class VideoMapper extends PhotoMapper {

    public static final String VIDEO = "video";
    public static final String GIF = "animated_gif";


    @Inject
    public VideoMapper() {
    }

    @Override
    protected boolean typeFilter(Medium__ medium__) {
        if (medium__.getType().equals(VIDEO) || medium__.getType().equals(GIF)) return true;
        return false;
    }

    @Override
    protected String getSourceUrl(Medium__ medium__) {
        try {
            return medium__.getVideoInfo().getVariants().get(0).getUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return SystemData.EMPTY_ALBUM_COVER;
        }
    }

    @Override
    protected int getMediaType() {
        return MediaItem.VIDEO;
    }
}
