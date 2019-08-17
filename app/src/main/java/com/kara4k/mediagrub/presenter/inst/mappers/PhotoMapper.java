package com.kara4k.mediagrub.presenter.inst.mappers;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.inst.photo.Data;
import com.kara4k.mediagrub.model.inst.photo.Dimensions;
import com.kara4k.mediagrub.model.inst.photo.Edge;
import com.kara4k.mediagrub.model.inst.photo.EdgeOwnerToTimelineMedia;
import com.kara4k.mediagrub.model.inst.photo.Node;
import com.kara4k.mediagrub.model.inst.photo.PageInfo;
import com.kara4k.mediagrub.model.inst.photo.PhotoResponse;
import com.kara4k.mediagrub.model.inst.photo.User;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PhotoMapper implements Function<PhotoResponse, Observable<MediaItem>> {

    private String mLastId;
    private long mAlbumSize;

    @Inject
    public PhotoMapper() {
    }

    @Override
    public Observable<MediaItem> apply(PhotoResponse photoResponse) {
        return Observable.just(photoResponse)
                .map(PhotoResponse::getData)
                .map(Data::getUser)
                .map(User::getEdgeOwnerToTimelineMedia)
                .map(EdgeOwnerToTimelineMedia::getEdges)
                .flatMapIterable(edges -> edges)
                .map(Edge::getNode)
                .map(this::map)
                .doFinally(() -> setupAlbumInfo(photoResponse));

    }

    protected void setupAlbumInfo(PhotoResponse photoResponse) {
        try {
            EdgeOwnerToTimelineMedia object = photoResponse.getData().getUser().getEdgeOwnerToTimelineMedia();
            mAlbumSize = object.getCount();
            PageInfo pageInfo = object.getPageInfo();
            mLastId = pageInfo.getEndCursor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MediaItem map(Node node) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setId(node.getId());
        mediaItem.setAlbumId("");
        mediaItem.setOwnerId("");
        mediaItem.setTitle(getTitle(node));
        mediaItem.setDescription("");
        mediaItem.setAddition(getAddition(node));
        mediaItem.setThumbUrl(node.getThumbnailSrc());
        mediaItem.setType(getType(node));
        mediaItem.setSourceUrl(getSourceUrl(node));
        return mediaItem;
    }

    private String getSourceUrl(Node node) {
        return node.getIsVideo() ? node.getShortcode() : node.getDisplayUrl();
    }

    private String getTitle(Node node) {
        try {
            return node.getEdgeMediaToCaption().getEdges().get(0).getNode().getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private int getType(Node node) {
        return node.getIsVideo() ? MediaItem.VIDEO : MediaItem.PHOTO;
    }

    private String getAddition(Node node) {
        Dimensions dimensions = node.getDimensions();
        return String.format(Locale.ENGLISH, "%dx%d",
                dimensions.getWidth(), dimensions.getHeight());
    }

    public String getLastId() {
        return mLastId;
    }

    public long getAlbumSize() {
        return mAlbumSize;
    }
}
