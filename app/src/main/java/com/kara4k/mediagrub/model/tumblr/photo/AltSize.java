
package com.kara4k.mediagrub.model.tumblr.photo;

import com.google.gson.annotations.SerializedName;

public class AltSize {

    @SerializedName("height")
    private Long mHeight;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("width")
    private Long mWidth;

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
