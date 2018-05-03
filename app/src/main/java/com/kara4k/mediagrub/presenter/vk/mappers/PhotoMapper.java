package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.vk.photo.Item;
import com.kara4k.mediagrub.model.vk.photo.PhotoResponse;
import com.kara4k.mediagrub.model.vk.photo.Response;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class PhotoMapper implements Function<PhotoResponse, Observable<MediaItem>> {

    private static final String UNKNOWN = "---";

    private long mTotal;

    @Inject
    public PhotoMapper() {
    }

    @Override
    public Observable<MediaItem> apply(PhotoResponse photoResponse) {
        return Observable.just(photoResponse)
                .filter(this::filterResponseError)
                .map(PhotoResponse::getResponse)
                .flatMapIterable(Response::getItems)
                .map(this::map)
                .doFinally(() -> runFinally(photoResponse));
    }

    private boolean filterResponseError(PhotoResponse response) throws Exception {
        if (response.getResponse() == null && response.getResponseError() != null) {
            String errorMsg = response.getResponseError().getErrorMsg();

            throw new Exception(errorMsg);
        }
        return true;
    }

    private void runFinally(PhotoResponse photoResponse) throws Exception {
        if (photoResponse == null) return;
        Response response = photoResponse.getResponse();
        if (response == null) return;
        if (response.getCount() != null) mTotal = response.getCount();
    }

    protected MediaItem map(Item item) {
        MediaItem mediaItem = new MediaItem();
        String id = String.valueOf(item.getId());
        String albumId = String.valueOf(item.getAlbumId());
        String ownerId = String.valueOf(item.getOwnerId());
        String addition = getAddition(item);

        String source = getSource(item);
        String thumb = getThumb(item);

        mediaItem.setId(id);
        mediaItem.setAlbumId(albumId);
        mediaItem.setOwnerId(ownerId);
        mediaItem.setTitle(item.getText());
        mediaItem.setDescription("");
        mediaItem.setAddition(addition);
        mediaItem.setThumbUrl(thumb);
        mediaItem.setSourceUrl(source);
        mediaItem.setType(MediaItem.PHOTO);
        return mediaItem;
    }

    protected String getAddition(Item item){
        Long width = item.getWidth();
        Long height = item.getHeight();

        if (width != null && height != null) {
            return String.format(Locale.ENGLISH, "%sx%s", width, height);
        }
        return UNKNOWN;
    }

    protected String getThumb(Item item) {
        String photo604 = item.getPhoto604();
        if (photo604 != null) return photo604;
        String photo130 = item.getPhoto130();
        if (photo130 != null) return photo130;
        String photo75 = item.getPhoto75();
        if (photo75 != null) return photo75;

        return "";
    }

    protected String getSource(Item item) {
        String photo2560 = item.getPhoto2560();
        if (photo2560 != null) return photo2560;
        String photo1280 = item.getPhoto1280();
        if (photo1280 != null) return photo1280;
        String photo807 = item.getPhoto807();
        if (photo807 != null) return item.getPhoto807();
        String photo604 = item.getPhoto604();
        if (photo604 != null) return photo604;
        String photo130 = item.getPhoto130();
        if (photo130 != null) return photo130;
        String photo75 = item.getPhoto75();
        if (photo75 != null) return photo75;

        return "";
    }

    public long getTotal() {
        return mTotal;
    }
}
