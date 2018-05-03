
package com.kara4k.mediagrub.model.tumblr.photo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("photos")
    private List<Photo> mPhotos;
    @SerializedName("summary")
    private String mSummary;


    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(List<Photo> photos) {
        mPhotos = photos;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

}
