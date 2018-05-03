package com.kara4k.mediagrub.presenter.twitter.mappers;


import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.twitter.media.ExtendedEntities;
import com.kara4k.mediagrub.model.twitter.media.Large_;
import com.kara4k.mediagrub.model.twitter.media.Medium__;
import com.kara4k.mediagrub.model.twitter.media.Tweet;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PhotoMapper implements Function<List<Tweet>, Observable<MediaItem>> {

    private static final String PHOTO = "photo";

    private String mMaxId;
    private boolean mHasMore = true;
    private String mTempText;

    @Inject
    public PhotoMapper() {
    }

    @Override
    public Observable<MediaItem> apply(List<Tweet> tweets) throws Exception {
        return Observable.fromIterable(tweets)
                .filter(tweet -> tweet.getExtendedEntities() != null)
                .filter(this::setupMediaText)
                .map(Tweet::getExtendedEntities)
                .map(ExtendedEntities::getMedia)
                .flatMapIterable(medium__s -> medium__s)
                .filter(this::typeFilter)
                .map(this::map)
                .doFinally(() -> setupOffsets(tweets));
    }

    protected boolean typeFilter(Medium__ medium__) {
        if (medium__.getType().equals(PHOTO)) return true;
        return false;
    }

    private boolean setupMediaText(Tweet tweet) {
        mTempText = tweet.getText();
        return true;
    }

    private void setupOffsets(List<Tweet> tweets) {
        if (tweets.size() == 1) {
            mHasMore = false;
            return;
        }
        mMaxId = tweets.get(tweets.size() - 1).getIdStr();
    }

    private MediaItem map(Medium__ medium__) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setId(medium__.getIdStr());
        mediaItem.setAlbumId("");
        mediaItem.setOwnerId("");
        mediaItem.setTitle(mTempText);
        mediaItem.setDescription("");
        mediaItem.setAddition(getAddition(medium__));
        mediaItem.setThumbUrl(medium__.getMediaUrlHttps());
        mediaItem.setSourceUrl(getSourceUrl(medium__));
        mediaItem.setType(getMediaType());
        return mediaItem;
    }

    protected int getMediaType() {
        return MediaItem.PHOTO;
    }

    protected String getSourceUrl(Medium__ medium__) {
        return medium__.getMediaUrlHttps();
    }

    private String getAddition(Medium__ medium__) {
        try {
            Large_ large = medium__.getSizes().getLarge();
            return String.format(Locale.ENGLISH, "%dx%d", large.getW(), large.getH());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemData.UNDEFINED_SIZE;
    }

    public boolean isHasMore() {
        return mHasMore;
    }

    public String getMaxId() {
        return mMaxId;
    }
}
