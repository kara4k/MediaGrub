
package com.kara4k.mediagrub.model.flickr.groupphotos;

import com.google.gson.annotations.SerializedName;

public class GroupPhotosResponse {

    @SerializedName("photos")
    private Photos mPhotos;
    @SerializedName("stat")
    private String mStat;
    @SerializedName("message")
    private String mErrorMessage;

    public Photos getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photos photos) {
        mPhotos = photos;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }
}
