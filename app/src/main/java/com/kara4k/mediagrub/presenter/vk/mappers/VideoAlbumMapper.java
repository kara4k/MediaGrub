package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.videoalbum.Item;
import com.kara4k.mediagrub.model.vk.videoalbum.Response;
import com.kara4k.mediagrub.model.vk.videoalbum.VideoAlbumResponse;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class VideoAlbumMapper implements Function<VideoAlbumResponse, Observable<AlbumItem>> {

    @Inject
    VideoAlbumMapper() {
    }

    @Override
    public Observable<AlbumItem> apply(VideoAlbumResponse videoAlbumResponse) throws Exception {
        return Observable.just(videoAlbumResponse)
                .filter(this::filterResponseError)
                .map(VideoAlbumResponse::getResponse)
                .flatMapIterable(Response::getItems)
                .map(this::map);
    }

    private boolean filterResponseError(VideoAlbumResponse response) throws Exception {
        if (response.getResponse() == null && response.getResponseError() != null) {
            String errorMsg = response.getResponseError().getErrorMsg();
            String message = String.format(Locale.ENGLISH, "Video: %s", errorMsg);

            throw new Exception(message);
        }
        return true;
    }

    private AlbumItem map(Item item) throws Exception {
        AlbumItem albumItem = new AlbumItem();
        String id = String.valueOf(item.getId());
        String ownerId = String.valueOf(item.getOwnerId());
        String size = String.valueOf(item.getCount());
        String title = item.getTitle();
        String coverUrl = item.getPhoto320();

        albumItem.setId(id);
        albumItem.setOwnerId(ownerId);
        albumItem.setSize(size);
        albumItem.setTitle(title);
        albumItem.setDescription("");
        albumItem.setCoverUrl(coverUrl);
        return albumItem;
    }
}
