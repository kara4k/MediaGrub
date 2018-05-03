package com.kara4k.mediagrub.presenter.flickr.mappers;


import android.util.Log;

import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.flickr.useralbum.Photoset;
import com.kara4k.mediagrub.model.flickr.useralbum.Photosets;
import com.kara4k.mediagrub.model.flickr.useralbum.PrimaryPhotoExtras;
import com.kara4k.mediagrub.model.flickr.useralbum.UserAlbumsResponse;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserAlbumsMapper implements Function<UserAlbumsResponse, Observable<AlbumItem>> {

    @Inject
    public UserAlbumsMapper() {
    }

    @Override
    public Observable<AlbumItem> apply(UserAlbumsResponse userAlbumsResponse) throws Exception {
        return Observable.just(userAlbumsResponse)
                .map(UserAlbumsResponse::getPhotosets)
                .map(Photosets::getPhotoset)
                .flatMapIterable(photosets -> photosets)
                .map(this::map);
    }

    private AlbumItem map(Photoset photoset) {
        AlbumItem albumItem = new AlbumItem();
        albumItem.setId(photoset.getId());
        albumItem.setOwnerId(photoset.getId());
        albumItem.setSize(String.valueOf(photoset.getPhotos()));
        albumItem.setTitle(getTitle(photoset));
        albumItem.setDescription(getDescription(photoset));
        albumItem.setCoverUrl(getCoverUrl(photoset));
        return albumItem;
    }

    private String getCoverUrl(Photoset photoset) {
        PrimaryPhotoExtras primaryPhotoExtras = photoset.getPrimaryPhotoExtras();

        String urlM = primaryPhotoExtras.getUrlM();
        if (urlM != null) return urlM;
        String urlO = primaryPhotoExtras.getUrlO();
        if (urlO != null) return urlO;
        String urlS = primaryPhotoExtras.getUrlS();
        if (urlS != null) return urlS;

        return SystemData.EMPTY_ALBUM_COVER;
    }

    private String getDescription(Photoset photoset) {
        return photoset.getDescription().get_content();
    }

    private String getTitle(Photoset photoset) {
        return photoset.getTitle().get_content();
    }
}
