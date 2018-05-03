
package com.kara4k.mediagrub.model.inst.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Hashtag {

    @SerializedName("edge_hashtag_to_content_advisory")
    private EdgeHashtagToContentAdvisory mEdgeHashtagToContentAdvisory;
    @SerializedName("edge_hashtag_to_media")
    private EdgeHashtagToMedia mEdgeHashtagToMedia;
    @SerializedName("edge_hashtag_to_top_posts")
    private EdgeHashtagToTopPosts mEdgeHashtagToTopPosts;
    @SerializedName("is_top_media_only")
    private Boolean mIsTopMediaOnly;
    @SerializedName("name")
    private String mName;

    public EdgeHashtagToContentAdvisory getEdgeHashtagToContentAdvisory() {
        return mEdgeHashtagToContentAdvisory;
    }

    public void setEdgeHashtagToContentAdvisory(EdgeHashtagToContentAdvisory edgeHashtagToContentAdvisory) {
        mEdgeHashtagToContentAdvisory = edgeHashtagToContentAdvisory;
    }

    public EdgeHashtagToMedia getEdgeHashtagToMedia() {
        return mEdgeHashtagToMedia;
    }

    public void setEdgeHashtagToMedia(EdgeHashtagToMedia edgeHashtagToMedia) {
        mEdgeHashtagToMedia = edgeHashtagToMedia;
    }

    public EdgeHashtagToTopPosts getEdgeHashtagToTopPosts() {
        return mEdgeHashtagToTopPosts;
    }

    public void setEdgeHashtagToTopPosts(EdgeHashtagToTopPosts edgeHashtagToTopPosts) {
        mEdgeHashtagToTopPosts = edgeHashtagToTopPosts;
    }

    public Boolean getIsTopMediaOnly() {
        return mIsTopMediaOnly;
    }

    public void setIsTopMediaOnly(Boolean isTopMediaOnly) {
        mIsTopMediaOnly = isTopMediaOnly;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
