package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.photoalbum.Item;
import com.kara4k.mediagrub.model.vk.photoalbum.PhotoAlbumResponse;
import com.kara4k.mediagrub.model.vk.photoalbum.Response;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PhotoAlbumMapper implements Function<PhotoAlbumResponse, Observable<AlbumItem>> {

    @Inject
    PhotoAlbumMapper() {
    }

    @Override
    public Observable<AlbumItem> apply(PhotoAlbumResponse photoAlbumResponse) throws Exception {
        return Observable.just(photoAlbumResponse)
                .filter(this::filterResponseError)
                .map(PhotoAlbumResponse::getResponse)
                .flatMapIterable(Response::getItems)
                .map(this::map);
    }

    private boolean filterResponseError(PhotoAlbumResponse response) throws Exception {
        if (response.getResponse() == null && response.getResponseError() != null) {
            String errorMsg = response.getResponseError().getErrorMsg();
            throw new Exception(errorMsg);
        }
        return true;
    }

    private AlbumItem map(Item item) throws Exception {
        AlbumItem albumItem = new AlbumItem();
        String id = String.valueOf(item.getId());
        String owner_id = String.valueOf(item.getOwnerId());
        String size = String.valueOf(item.getSize());
        String title = item.getTitle();
        String description = item.getDescription();
        String coverUrl = item.getThumbSrc();

        albumItem.setId(id);
        albumItem.setOwnerId(owner_id);
        albumItem.setSize(size);
        albumItem.setTitle(title);
        albumItem.setDescription(description);
        albumItem.setCoverUrl(coverUrl);
        return albumItem;
    }
}
