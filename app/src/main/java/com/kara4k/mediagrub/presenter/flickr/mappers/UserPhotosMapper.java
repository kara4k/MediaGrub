package com.kara4k.mediagrub.presenter.flickr.mappers;


import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.flickr.userphotos.Photo;
import com.kara4k.mediagrub.model.flickr.userphotos.Photoset;
import com.kara4k.mediagrub.model.flickr.userphotos.UserPhotosResponse;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserPhotosMapper implements Function<UserPhotosResponse, Observable<MediaItem>> {

    private int mPage = 1;
    private int mPages;

    @Inject
    public UserPhotosMapper() {
    }

    @Override
    public Observable<MediaItem> apply(UserPhotosResponse userPhotosResponse) throws Exception {
        return Observable.just(userPhotosResponse)
                .filter(this::errorResponceFilter)
                .map(UserPhotosResponse::getPhotoset)
                .map(Photoset::getPhoto)
                .flatMapIterable(photos -> photos)
                .map(this::map)
                .doFinally(() -> setupOffsets(userPhotosResponse));
    }

    private void setupOffsets(UserPhotosResponse userPhotosResponse) {
        try {
            mPage++;
            mPages = userPhotosResponse.getPhotoset().getPages();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean errorResponceFilter(UserPhotosResponse userPhotosResponse) throws Exception {
        if (userPhotosResponse.getPhotoset() == null
                && userPhotosResponse.getErrorMessage() != null) {
            throw new Exception(userPhotosResponse.getErrorMessage());
        }
        return true;
    }

    private MediaItem map(Photo photo) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setId(photo.getId());
        mediaItem.setAlbumId("");
        mediaItem.setOwnerId("");
        mediaItem.setTitle(photo.getTitle());
        mediaItem.setDescription("");
        mediaItem.setThumbUrl(getThumbUrl(photo));
        mediaItem.setType(MediaItem.PHOTO);

        setSourceUrl(photo, mediaItem);
        return mediaItem;
    }

    private String getThumbUrl(Photo photo) {
        String urlM = photo.getUrlM();
        if (urlM != null) return urlM;

        String urlZ = photo.getUrlZ();
        if (urlZ != null) return urlZ;

        String urlC = photo.getUrlC();
        if (urlC != null) return urlC;

        String urlH = photo.getUrlH();
        if (urlH != null) return urlH;

        String urlK = photo.getUrlK();
        if (urlK != null) return urlK;

        String urlO = photo.getUrlO();
        if (urlO != null) return urlO;

        String urlN = photo.getUrlN();
        if (urlN != null) return urlN;

        return SystemData.EMPTY_ALBUM_COVER;
    }

    private void setSourceUrl(Photo photo, MediaItem mediaItem) {
        String urlO = photo.getUrlO();
        if (urlO != null) {
            mediaItem.setSourceUrl(urlO);
            mediaItem.setAddition(getAddition(photo.getWidthO(), photo.getHeightO()));
            return;
        }

        String urlK = photo.getUrlK();
        if (urlK != null) {
            mediaItem.setSourceUrl(urlK);
            mediaItem.setAddition(getAddition(photo.getWidthK(), String.valueOf(photo.getHeightK())));
            return;
        }

        String urlH = photo.getUrlH();
        if (urlH != null) {
            mediaItem.setSourceUrl(urlH);
            mediaItem.setAddition(getAddition(photo.getWidthH(), String.valueOf(photo.getHeightH())));
            return;
        }

        String urlC = photo.getUrlC();
        if (urlC != null) {
            mediaItem.setSourceUrl(urlC);
            mediaItem.setAddition(getAddition(photo.getWidthC(), String.valueOf(photo.getHeightC())));
            return;
        }

        String urlZ = photo.getUrlZ();
        if (urlZ != null) {
            mediaItem.setSourceUrl(urlZ);
            mediaItem.setAddition(getAddition(photo.getWidthZ(), photo.getHeightZ()));
            return;
        }

        String urlM = photo.getUrlM();
        if (urlM != null) {
            mediaItem.setSourceUrl(urlM);
            mediaItem.setAddition(getAddition(photo.getWidthM(), photo.getHeightM()));
            return;
        }

        String urlN = photo.getUrlN();
        if (urlN != null) {
            mediaItem.setSourceUrl(urlN);
            mediaItem.setAddition(getAddition(photo.getWidthN(), String.valueOf(photo.getHeightN())));
            return;
        }

        if (mediaItem.getSourceUrl() == null) {
            mediaItem.setSourceUrl(SystemData.EMPTY_ALBUM_COVER);
            mediaItem.setAddition(SystemData.UNDEFINED_SIZE);
        }
    }

    private String getAddition(String width, String height) {
        return String.format(Locale.ENGLISH, "%sx%s", width, height);
    }

    public int getPage() {
        return mPage;
    }

    public int getPages() {
        return mPages;
    }
}
