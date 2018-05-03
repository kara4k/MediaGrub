
package com.kara4k.mediagrub.model.inst.search;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Node {

    @SerializedName("comments_disabled")
    private Boolean mCommentsDisabled;
    @SerializedName("dimensions")
    private Dimensions mDimensions;
    @SerializedName("display_url")
    private String mDisplayUrl;
    @SerializedName("edge_liked_by")
    private EdgeLikedBy mEdgeLikedBy;
    @SerializedName("edge_media_to_caption")
    private EdgeMediaToCaption mEdgeMediaToCaption;
    @SerializedName("edge_media_to_comment")
    private EdgeMediaToComment mEdgeMediaToComment;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_video")
    private Boolean mIsVideo;
    @SerializedName("owner")
    private Owner mOwner;
    @SerializedName("shortcode")
    private String mShortcode;
    @SerializedName("taken_at_timestamp")
    private Long mTakenAtTimestamp;
    @SerializedName("text")
    private String mText;
    @SerializedName("thumbnail_resources")
    private List<ThumbnailResource> mThumbnailResources;
    @SerializedName("thumbnail_src")
    private String mThumbnailSrc;
    @SerializedName("video_view_count")
    private Long mVideoViewCount;

    public Boolean getCommentsDisabled() {
        return mCommentsDisabled;
    }

    public void setCommentsDisabled(Boolean commentsDisabled) {
        mCommentsDisabled = commentsDisabled;
    }

    public Dimensions getDimensions() {
        return mDimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        mDimensions = dimensions;
    }

    public String getDisplayUrl() {
        return mDisplayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        mDisplayUrl = displayUrl;
    }

    public EdgeLikedBy getEdgeLikedBy() {
        return mEdgeLikedBy;
    }

    public void setEdgeLikedBy(EdgeLikedBy edgeLikedBy) {
        mEdgeLikedBy = edgeLikedBy;
    }

    public EdgeMediaToCaption getEdgeMediaToCaption() {
        return mEdgeMediaToCaption;
    }

    public void setEdgeMediaToCaption(EdgeMediaToCaption edgeMediaToCaption) {
        mEdgeMediaToCaption = edgeMediaToCaption;
    }

    public EdgeMediaToComment getEdgeMediaToComment() {
        return mEdgeMediaToComment;
    }

    public void setEdgeMediaToComment(EdgeMediaToComment edgeMediaToComment) {
        mEdgeMediaToComment = edgeMediaToComment;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIsVideo() {
        return mIsVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        mIsVideo = isVideo;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public void setOwner(Owner owner) {
        mOwner = owner;
    }

    public String getShortcode() {
        return mShortcode;
    }

    public void setShortcode(String shortcode) {
        mShortcode = shortcode;
    }

    public Long getTakenAtTimestamp() {
        return mTakenAtTimestamp;
    }

    public void setTakenAtTimestamp(Long takenAtTimestamp) {
        mTakenAtTimestamp = takenAtTimestamp;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public List<ThumbnailResource> getThumbnailResources() {
        return mThumbnailResources;
    }

    public void setThumbnailResources(List<ThumbnailResource> thumbnailResources) {
        mThumbnailResources = thumbnailResources;
    }

    public String getThumbnailSrc() {
        return mThumbnailSrc;
    }

    public void setThumbnailSrc(String thumbnailSrc) {
        mThumbnailSrc = thumbnailSrc;
    }

    public Long getVideoViewCount() {
        return mVideoViewCount;
    }

    public void setVideoViewCount(Long videoViewCount) {
        mVideoViewCount = videoViewCount;
    }

}
