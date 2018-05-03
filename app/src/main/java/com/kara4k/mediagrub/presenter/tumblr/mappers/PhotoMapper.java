package com.kara4k.mediagrub.presenter.tumblr.mappers;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.tumblr.photo.AltSize;
import com.kara4k.mediagrub.model.tumblr.photo.OriginalSize;
import com.kara4k.mediagrub.model.tumblr.photo.Photo;
import com.kara4k.mediagrub.model.tumblr.photo.PhotoResponse;
import com.kara4k.mediagrub.model.tumblr.photo.Post;
import com.kara4k.mediagrub.model.tumblr.photo.Response;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PhotoMapper implements Function<PhotoResponse, Observable<MediaItem>> {

    private static final int THUMB_MIN = 300;
    private static final int THUMB_MAX = 500;

    private int mOffset = 0;
    private Long mTotalPosts;

    @Inject
    public PhotoMapper() {
    }

    @Override
    public Observable<MediaItem> apply(PhotoResponse photoResponse) throws Exception {
        return Observable.just(photoResponse)
                .map(PhotoResponse::getResponse)
                .map(Response::getPosts)
                .flatMapIterable(posts -> posts)
                .map(Post::getPhotos)
                .flatMapIterable(photos -> photos)
                .map(this::map)
                .doFinally(() -> setupOffsets(photoResponse));
    }

    private void setupOffsets(PhotoResponse photoResponse) {
        try {
            mOffset += photoResponse.getResponse().getPosts().size();
            mTotalPosts = photoResponse.getResponse().getTotalPosts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MediaItem map(Photo photo) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setId(getId(photo));
        mediaItem.setAlbumId("");
        mediaItem.setOwnerId("");
        mediaItem.setTitle(photo.getCaption());
        mediaItem.setDescription("");
        mediaItem.setAddition(getAddition(photo));
        mediaItem.setThumbUrl(getThumbnail(photo));
        mediaItem.setSourceUrl(getSourceUrl(photo));
        mediaItem.setType(MediaItem.PHOTO);
        return mediaItem;
    }

    private String getId(Photo photo) {
        try {
            String url = photo.getOriginalSize().getUrl();
            String[] split = url.split("/");
            String name = split[split.length - 1];
            return name.split("_")[1];
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(System.currentTimeMillis());
        }
    }

    private String getThumbnail(Photo photo) {
        List<AltSize> altSizes = photo.getAltSizes();

        for (int i = 0; i < altSizes.size(); i++) {
            AltSize altSize = altSizes.get(i);
            Long width = altSize.getWidth();
            Long height = altSize.getHeight();

            if ((width > THUMB_MIN && height > THUMB_MIN)
                    && (width < THUMB_MAX && height < THUMB_MAX)){
                return altSize.getUrl();
            }
        }

        return photo.getOriginalSize().getUrl();
    }

    private String getAddition(Photo photo) {
        OriginalSize originalSize = photo.getOriginalSize();
        return String.format(Locale.ENGLISH,
                "%dx%d", originalSize.getWidth(), originalSize.getHeight());
    }

    private String getSourceUrl(Photo photo) {
        return photo.getOriginalSize().getUrl();
    }

    public int getOffset() {
        return mOffset;
    }

    public Long getTotalPosts() {
        return mTotalPosts;
    }
}
