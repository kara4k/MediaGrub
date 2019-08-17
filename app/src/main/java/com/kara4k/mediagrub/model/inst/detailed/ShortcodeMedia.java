
package com.kara4k.mediagrub.model.inst.detailed;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ShortcodeMedia {

    @SerializedName("caption_is_edited")
    private Boolean mCaptionIsEdited;
    @SerializedName("comments_disabled")
    private Boolean mCommentsDisabled;
    @SerializedName("dash_info")
    private DashInfo mDashInfo;
    @SerializedName("dimensions")
    private Dimensions mDimensions;
    @SerializedName("display_resources")
    private List<DisplayResource> mDisplayResources;
    @SerializedName("display_url")
    private String mDisplayUrl;
    @SerializedName("edge_media_preview_like")
    private EdgeMediaPreviewLike mEdgeMediaPreviewLike;
    @SerializedName("edge_media_to_caption")
    private EdgeMediaToCaption mEdgeMediaToCaption;
    @SerializedName("edge_media_to_comment")
    private EdgeMediaToComment mEdgeMediaToComment;
    @SerializedName("edge_media_to_sponsor_user")
    private EdgeMediaToSponsorUser mEdgeMediaToSponsorUser;
    @SerializedName("edge_media_to_tagged_user")
    private EdgeMediaToTaggedUser mEdgeMediaToTaggedUser;
    @SerializedName("edge_web_media_to_related_media")
    private EdgeWebMediaToRelatedMedia mEdgeWebMediaToRelatedMedia;
    @SerializedName("encoding_status")
    private Object mEncodingStatus;
    @SerializedName("gating_info")
    private Object mGatingInfo;
    @SerializedName("has_ranked_comments")
    private Boolean mHasRankedComments;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_ad")
    private Boolean mIsAd;
    @SerializedName("is_published")
    private Boolean mIsPublished;
    @SerializedName("is_video")
    private Boolean mIsVideo;
    @SerializedName("location")
    private Object mLocation;
    @SerializedName("media_preview")
    private String mMediaPreview;
    @SerializedName("owner")
    private Owner mOwner;
    @SerializedName("product_type")
    private String mProductType;
    @SerializedName("shortcode")
    private String mShortcode;
    @SerializedName("should_log_client_event")
    private Boolean mShouldLogClientEvent;
    @SerializedName("taken_at_timestamp")
    private Long mTakenAtTimestamp;
    @SerializedName("thumbnail_src")
    private String mThumbnailSrc;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("tracking_token")
    private String mTrackingToken;
    @SerializedName("video_duration")
    private Double mVideoDuration;
    @SerializedName("video_url")
    private String mVideoUrl;
    @SerializedName("video_view_count")
    private Long mVideoViewCount;
    @SerializedName("viewer_can_reshare")
    private Boolean mViewerCanReshare;
    @SerializedName("viewer_has_liked")
    private Boolean mViewerHasLiked;
    @SerializedName("viewer_has_saved")
    private Boolean mViewerHasSaved;
    @SerializedName("viewer_has_saved_to_collection")
    private Boolean mViewerHasSavedToCollection;
    @SerializedName("viewer_in_photo_of_you")
    private Boolean mViewerInPhotoOfYou;
    @SerializedName("__typename")
    private String m_Typename;

    public Boolean getCaptionIsEdited() {
        return mCaptionIsEdited;
    }

    public void setCaptionIsEdited(Boolean captionIsEdited) {
        mCaptionIsEdited = captionIsEdited;
    }

    public Boolean getCommentsDisabled() {
        return mCommentsDisabled;
    }

    public void setCommentsDisabled(Boolean commentsDisabled) {
        mCommentsDisabled = commentsDisabled;
    }

    public DashInfo getDashInfo() {
        return mDashInfo;
    }

    public void setDashInfo(DashInfo dashInfo) {
        mDashInfo = dashInfo;
    }

    public Dimensions getDimensions() {
        return mDimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        mDimensions = dimensions;
    }

    public List<DisplayResource> getDisplayResources() {
        return mDisplayResources;
    }

    public void setDisplayResources(List<DisplayResource> displayResources) {
        mDisplayResources = displayResources;
    }

    public String getDisplayUrl() {
        return mDisplayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        mDisplayUrl = displayUrl;
    }

    public EdgeMediaPreviewLike getEdgeMediaPreviewLike() {
        return mEdgeMediaPreviewLike;
    }

    public void setEdgeMediaPreviewLike(EdgeMediaPreviewLike edgeMediaPreviewLike) {
        mEdgeMediaPreviewLike = edgeMediaPreviewLike;
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

    public EdgeMediaToSponsorUser getEdgeMediaToSponsorUser() {
        return mEdgeMediaToSponsorUser;
    }

    public void setEdgeMediaToSponsorUser(EdgeMediaToSponsorUser edgeMediaToSponsorUser) {
        mEdgeMediaToSponsorUser = edgeMediaToSponsorUser;
    }

    public EdgeMediaToTaggedUser getEdgeMediaToTaggedUser() {
        return mEdgeMediaToTaggedUser;
    }

    public void setEdgeMediaToTaggedUser(EdgeMediaToTaggedUser edgeMediaToTaggedUser) {
        mEdgeMediaToTaggedUser = edgeMediaToTaggedUser;
    }

    public EdgeWebMediaToRelatedMedia getEdgeWebMediaToRelatedMedia() {
        return mEdgeWebMediaToRelatedMedia;
    }

    public void setEdgeWebMediaToRelatedMedia(EdgeWebMediaToRelatedMedia edgeWebMediaToRelatedMedia) {
        mEdgeWebMediaToRelatedMedia = edgeWebMediaToRelatedMedia;
    }

    public Object getEncodingStatus() {
        return mEncodingStatus;
    }

    public void setEncodingStatus(Object encodingStatus) {
        mEncodingStatus = encodingStatus;
    }

    public Object getGatingInfo() {
        return mGatingInfo;
    }

    public void setGatingInfo(Object gatingInfo) {
        mGatingInfo = gatingInfo;
    }

    public Boolean getHasRankedComments() {
        return mHasRankedComments;
    }

    public void setHasRankedComments(Boolean hasRankedComments) {
        mHasRankedComments = hasRankedComments;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIsAd() {
        return mIsAd;
    }

    public void setIsAd(Boolean isAd) {
        mIsAd = isAd;
    }

    public Boolean getIsPublished() {
        return mIsPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        mIsPublished = isPublished;
    }

    public Boolean getIsVideo() {
        return mIsVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        mIsVideo = isVideo;
    }

    public Object getLocation() {
        return mLocation;
    }

    public void setLocation(Object location) {
        mLocation = location;
    }

    public String getMediaPreview() {
        return mMediaPreview;
    }

    public void setMediaPreview(String mediaPreview) {
        mMediaPreview = mediaPreview;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public void setOwner(Owner owner) {
        mOwner = owner;
    }

    public String getProductType() {
        return mProductType;
    }

    public void setProductType(String productType) {
        mProductType = productType;
    }

    public String getShortcode() {
        return mShortcode;
    }

    public void setShortcode(String shortcode) {
        mShortcode = shortcode;
    }

    public Boolean getShouldLogClientEvent() {
        return mShouldLogClientEvent;
    }

    public void setShouldLogClientEvent(Boolean shouldLogClientEvent) {
        mShouldLogClientEvent = shouldLogClientEvent;
    }

    public Long getTakenAtTimestamp() {
        return mTakenAtTimestamp;
    }

    public void setTakenAtTimestamp(Long takenAtTimestamp) {
        mTakenAtTimestamp = takenAtTimestamp;
    }

    public String getThumbnailSrc() {
        return mThumbnailSrc;
    }

    public void setThumbnailSrc(String thumbnailSrc) {
        mThumbnailSrc = thumbnailSrc;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTrackingToken() {
        return mTrackingToken;
    }

    public void setTrackingToken(String trackingToken) {
        mTrackingToken = trackingToken;
    }

    public Double getVideoDuration() {
        return mVideoDuration;
    }

    public void setVideoDuration(Double videoDuration) {
        mVideoDuration = videoDuration;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }

    public Long getVideoViewCount() {
        return mVideoViewCount;
    }

    public void setVideoViewCount(Long videoViewCount) {
        mVideoViewCount = videoViewCount;
    }

    public Boolean getViewerCanReshare() {
        return mViewerCanReshare;
    }

    public void setViewerCanReshare(Boolean viewerCanReshare) {
        mViewerCanReshare = viewerCanReshare;
    }

    public Boolean getViewerHasLiked() {
        return mViewerHasLiked;
    }

    public void setViewerHasLiked(Boolean viewerHasLiked) {
        mViewerHasLiked = viewerHasLiked;
    }

    public Boolean getViewerHasSaved() {
        return mViewerHasSaved;
    }

    public void setViewerHasSaved(Boolean viewerHasSaved) {
        mViewerHasSaved = viewerHasSaved;
    }

    public Boolean getViewerHasSavedToCollection() {
        return mViewerHasSavedToCollection;
    }

    public void setViewerHasSavedToCollection(Boolean viewerHasSavedToCollection) {
        mViewerHasSavedToCollection = viewerHasSavedToCollection;
    }

    public Boolean getViewerInPhotoOfYou() {
        return mViewerInPhotoOfYou;
    }

    public void setViewerInPhotoOfYou(Boolean viewerInPhotoOfYou) {
        mViewerInPhotoOfYou = viewerInPhotoOfYou;
    }

    public String get_Typename() {
        return m_Typename;
    }

    public void set_Typename(String _Typename) {
        m_Typename = _Typename;
    }

}
