
package com.kara4k.mediagrub.model.twitter.media;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("indices")
    @Expose
    private List<Integer> indices = null;
    @SerializedName("media_url")
    @Expose
    private String mediaUrl;
    @SerializedName("media_url_https")
    @Expose
    private String mediaUrlHttps;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("display_url")
    @Expose
    private String displayUrl;
    @SerializedName("expanded_url")
    @Expose
    private String expandedUrl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sizes")
    @Expose
    private Sizes sizes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrlHttps() {
        return mediaUrlHttps;
    }

    public void setMediaUrlHttps(String mediaUrlHttps) {
        this.mediaUrlHttps = mediaUrlHttps;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

}
