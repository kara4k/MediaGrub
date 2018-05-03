
package com.kara4k.mediagrub.model.twitter.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {

    @SerializedName("bitrate")
    @Expose
    private Integer bitrate;
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("url")
    @Expose
    private String url;

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
