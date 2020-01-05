package com.kara4k.mediagrub.presenter.tumblr.mappers;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.tumblr.photo.AltSize;
import com.kara4k.mediagrub.model.tumblr.photo.OriginalSize;
import com.kara4k.mediagrub.model.tumblr.photo.Photo;
import com.kara4k.mediagrub.model.tumblr.photo.PhotoResponse;
import com.kara4k.mediagrub.model.tumblr.photo.Post;
import com.kara4k.mediagrub.model.tumblr.photo.Response;
import com.kara4k.mediagrub.other.Tools;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
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

    private static final String POST_TYPE_TEXT = "text";

    @Inject
    public PhotoMapper() {
    }

    @Override
    public Observable<MediaItem> apply(final PhotoResponse photoResponse) throws Exception {
        return Observable.just(photoResponse)
                .map(PhotoResponse::getResponse)
                .map(Response::getPosts)
                .flatMapIterable(posts -> posts)
                .filter(post -> post.getPhotos() != null)
                .map(Post::getPhotos)
                .flatMapIterable(photos -> photos)
                .map(this::map)
                .mergeWith(getTextExtractedItems(photoResponse))
                .doFinally(() -> setupOffsets(photoResponse));
    }

    private Observable<MediaItem> getTextExtractedItems(final PhotoResponse photoResponse) {
        return Observable.just(photoResponse)
                .map(PhotoResponse::getResponse)
                .map(Response::getPosts)
                .flatMapIterable(posts -> posts)
                .filter(post -> post.getType().equals(POST_TYPE_TEXT))
                .filter(post -> StringUtils.isNotEmpty(post.getBody()))
                .map(this::mapPhotoItemsFromTextPost)
                .flatMapIterable(mediaItems -> mediaItems);
    }

    private void setupOffsets(final PhotoResponse photoResponse) {
        try {
            mOffset += photoResponse.getResponse().getPosts().size();
            mTotalPosts = photoResponse.getResponse().getTotalPosts();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private List<MediaItem> mapPhotoItemsFromTextPost(final Post post) {
        final Document document = Jsoup.parse(post.getBody());
        final Elements elements = document.select("img[src]");

        final List<MediaItem> photoItems = new ArrayList<>(elements.size());

        for (final Element x : elements) {
            final String photoSrc = x.attr("src");

            if (StringUtils.isEmpty(photoSrc)) {
                continue;
            }

            final MediaItem mediaItem = new MediaItem();
            mediaItem.setId(Tools.idFromPhotoUrl(photoSrc));
            mediaItem.setAlbumId("");
            mediaItem.setOwnerId("");
            mediaItem.setTitle(post.getTitle());
            mediaItem.setDescription("");
            mediaItem.setAddition("");
            mediaItem.setThumbUrl(photoSrc);
            mediaItem.setSourceUrl(photoSrc);
            mediaItem.setType(MediaItem.PHOTO);

            photoItems.add(mediaItem);
        }

        return photoItems;
    }

    private MediaItem map(final Photo photo) {
        final MediaItem mediaItem = new MediaItem();
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

    private String getId(final Photo photo) {
        try {
            final String url = photo.getOriginalSize().getUrl();
            final String[] split = url.split("/");
            final String name = split[split.length - 1];
            return name.split("_")[1];
        } catch (final Exception e) {
            e.printStackTrace();
            return String.valueOf(System.currentTimeMillis());
        }
    }

    private String getThumbnail(final Photo photo) {
        final List<AltSize> altSizes = photo.getAltSizes();

        for (int i = 0; i < altSizes.size(); i++) {
            final AltSize altSize = altSizes.get(i);
            final Long width = altSize.getWidth();
            final Long height = altSize.getHeight();

            if ((width > THUMB_MIN && height > THUMB_MIN)
                    && (width < THUMB_MAX && height < THUMB_MAX)) {
                return altSize.getUrl();
            }
        }

        return photo.getOriginalSize().getUrl();
    }

    private String getAddition(final Photo photo) {
        final OriginalSize originalSize = photo.getOriginalSize();
        return String.format(Locale.ENGLISH,
                "%dx%d", originalSize.getWidth(), originalSize.getHeight());
    }

    private String getSourceUrl(final Photo photo) {
        return photo.getOriginalSize().getUrl();
    }

    public int getOffset() {
        return mOffset;
    }

    public Long getTotalPosts() {
        return mTotalPosts;
    }
}
