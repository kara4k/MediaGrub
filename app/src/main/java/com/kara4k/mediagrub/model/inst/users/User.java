
package com.kara4k.mediagrub.model.inst.users;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class User {

    @SerializedName("biography")
    private String mBiography;
    @SerializedName("blocked_by_viewer")
    private Boolean mBlockedByViewer;
    @SerializedName("connected_fb_page")
    private Object mConnectedFbPage;
    @SerializedName("country_block")
    private Boolean mCountryBlock;
    @SerializedName("edge_follow")
    private EdgeFollow mEdgeFollow;
    @SerializedName("edge_followed_by")
    private EdgeFollowedBy mEdgeFollowedBy;
    @SerializedName("edge_media_collections")
    private EdgeMediaCollections mEdgeMediaCollections;
    @SerializedName("edge_owner_to_timeline_media")
    private EdgeOwnerToTimelineMedia mEdgeOwnerToTimelineMedia;
    @SerializedName("edge_saved_media")
    private EdgeSavedMedia mEdgeSavedMedia;
    @SerializedName("external_url")
    private Object mExternalUrl;
    @SerializedName("external_url_linkshimmed")
    private Object mExternalUrlLinkshimmed;
    @SerializedName("followed_by_viewer")
    private Boolean mFollowedByViewer;
    @SerializedName("follows_viewer")
    private Boolean mFollowsViewer;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("has_blocked_viewer")
    private Boolean mHasBlockedViewer;
    @SerializedName("has_requested_viewer")
    private Boolean mHasRequestedViewer;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_private")
    private Boolean mIsPrivate;
    @SerializedName("is_verified")
    private Boolean mIsVerified;
    @SerializedName("mutual_followers")
    private Object mMutualFollowers;
    @SerializedName("profile_pic_url")
    private String mProfilePicUrl;
    @SerializedName("profile_pic_url_hd")
    private String mProfilePicUrlHd;
    @SerializedName("requested_by_viewer")
    private Boolean mRequestedByViewer;
    @SerializedName("username")
    private String mUsername;

    public String getBiography() {
        return mBiography;
    }

    public void setBiography(String biography) {
        mBiography = biography;
    }

    public Boolean getBlockedByViewer() {
        return mBlockedByViewer;
    }

    public void setBlockedByViewer(Boolean blockedByViewer) {
        mBlockedByViewer = blockedByViewer;
    }

    public Object getConnectedFbPage() {
        return mConnectedFbPage;
    }

    public void setConnectedFbPage(Object connectedFbPage) {
        mConnectedFbPage = connectedFbPage;
    }

    public Boolean getCountryBlock() {
        return mCountryBlock;
    }

    public void setCountryBlock(Boolean countryBlock) {
        mCountryBlock = countryBlock;
    }

    public EdgeFollow getEdgeFollow() {
        return mEdgeFollow;
    }

    public void setEdgeFollow(EdgeFollow edgeFollow) {
        mEdgeFollow = edgeFollow;
    }

    public EdgeFollowedBy getEdgeFollowedBy() {
        return mEdgeFollowedBy;
    }

    public void setEdgeFollowedBy(EdgeFollowedBy edgeFollowedBy) {
        mEdgeFollowedBy = edgeFollowedBy;
    }

    public EdgeMediaCollections getEdgeMediaCollections() {
        return mEdgeMediaCollections;
    }

    public void setEdgeMediaCollections(EdgeMediaCollections edgeMediaCollections) {
        mEdgeMediaCollections = edgeMediaCollections;
    }

    public EdgeOwnerToTimelineMedia getEdgeOwnerToTimelineMedia() {
        return mEdgeOwnerToTimelineMedia;
    }

    public void setEdgeOwnerToTimelineMedia(EdgeOwnerToTimelineMedia edgeOwnerToTimelineMedia) {
        mEdgeOwnerToTimelineMedia = edgeOwnerToTimelineMedia;
    }

    public EdgeSavedMedia getEdgeSavedMedia() {
        return mEdgeSavedMedia;
    }

    public void setEdgeSavedMedia(EdgeSavedMedia edgeSavedMedia) {
        mEdgeSavedMedia = edgeSavedMedia;
    }

    public Object getExternalUrl() {
        return mExternalUrl;
    }

    public void setExternalUrl(Object externalUrl) {
        mExternalUrl = externalUrl;
    }

    public Object getExternalUrlLinkshimmed() {
        return mExternalUrlLinkshimmed;
    }

    public void setExternalUrlLinkshimmed(Object externalUrlLinkshimmed) {
        mExternalUrlLinkshimmed = externalUrlLinkshimmed;
    }

    public Boolean getFollowedByViewer() {
        return mFollowedByViewer;
    }

    public void setFollowedByViewer(Boolean followedByViewer) {
        mFollowedByViewer = followedByViewer;
    }

    public Boolean getFollowsViewer() {
        return mFollowsViewer;
    }

    public void setFollowsViewer(Boolean followsViewer) {
        mFollowsViewer = followsViewer;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public Boolean getHasBlockedViewer() {
        return mHasBlockedViewer;
    }

    public void setHasBlockedViewer(Boolean hasBlockedViewer) {
        mHasBlockedViewer = hasBlockedViewer;
    }

    public Boolean getHasRequestedViewer() {
        return mHasRequestedViewer;
    }

    public void setHasRequestedViewer(Boolean hasRequestedViewer) {
        mHasRequestedViewer = hasRequestedViewer;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIsPrivate() {
        return mIsPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        mIsPrivate = isPrivate;
    }

    public Boolean getIsVerified() {
        return mIsVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        mIsVerified = isVerified;
    }

    public Object getMutualFollowers() {
        return mMutualFollowers;
    }

    public void setMutualFollowers(Object mutualFollowers) {
        mMutualFollowers = mutualFollowers;
    }

    public String getProfilePicUrl() {
        return mProfilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        mProfilePicUrl = profilePicUrl;
    }

    public String getProfilePicUrlHd() {
        return mProfilePicUrlHd;
    }

    public void setProfilePicUrlHd(String profilePicUrlHd) {
        mProfilePicUrlHd = profilePicUrlHd;
    }

    public Boolean getRequestedByViewer() {
        return mRequestedByViewer;
    }

    public void setRequestedByViewer(Boolean requestedByViewer) {
        mRequestedByViewer = requestedByViewer;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
