package com.kara4k.mediagrub.presenter.inst.mappers;


import android.util.Log;

import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.inst.search.Data;
import com.kara4k.mediagrub.model.inst.search.Dimensions;
import com.kara4k.mediagrub.model.inst.search.Edge;
import com.kara4k.mediagrub.model.inst.search.EdgeHashtagToMedia;
import com.kara4k.mediagrub.model.inst.search.Hashtag;
import com.kara4k.mediagrub.model.inst.search.Node;
import com.kara4k.mediagrub.model.inst.search.SearchResponse;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class SearchMapper implements Function<SearchResponse, Observable<MediaItem>> {

    private String mLastId;
    private long mAlbumSize;

    @Inject
    public SearchMapper() {
    }

    @Override
    public Observable<MediaItem> apply(SearchResponse searchResponse) throws Exception {
      return Observable.just(searchResponse)
               .map(SearchResponse::getData)
               .map(Data::getHashtag)
               .map(Hashtag::getEdgeHashtagToMedia)
               .map(EdgeHashtagToMedia::getEdges)
               .flatMapIterable(edges -> edges)
               .map(Edge::getNode)
               .map(this::map)
               .doFinally(() -> setupAlbumInfo(searchResponse));

    }

    protected void setupAlbumInfo(SearchResponse searchResponse) {
        try {
            EdgeHashtagToMedia object = searchResponse.getData().getHashtag().getEdgeHashtagToMedia();
            mAlbumSize = object.getCount();
            mLastId = object.getPageInfo().getEndCursor();
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
        mediaItem.setSourceUrl(node.getDisplayUrl());
        mediaItem.setType(getType(node));
        return mediaItem;
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
