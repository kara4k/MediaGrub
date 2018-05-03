
package com.kara4k.mediagrub.model.flickr.userphotos;

import com.google.gson.annotations.SerializedName;

public class UserPhotosResponse {

    @SerializedName("photoset")
    private Photoset mPhotoset;
    @SerializedName("stat")
    private String mStat;
    @SerializedName("message")
    private String mErrorMessage;

    public Photoset getPhotoset() {
        return mPhotoset;
    }

    public void setPhotoset(Photoset photoset) {
        mPhotoset = photoset;
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
