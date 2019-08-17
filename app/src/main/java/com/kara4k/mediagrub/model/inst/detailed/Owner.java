
package com.kara4k.mediagrub.model.inst.detailed;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Owner {

    @SerializedName("blocked_by_viewer")
    private Boolean mBlockedByViewer;
    @SerializedName("followed_by_viewer")
    private Boolean mFollowedByViewer;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("has_blocked_viewer")
    private Boolean mHasBlockedViewer;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_private")
    private Boolean mIsPrivate;
    @SerializedName("is_unpublished")
    private Boolean mIsUnpublished;
    @SerializedName("is_verified")
    private Boolean mIsVerified;
    @SerializedName("profile_pic_url")
    private String mProfilePicUrl;
    @SerializedName("requested_by_viewer")
    private Boolean mRequestedByViewer;
    @SerializedName("username")
    private String mUsername;

    public Boolean getBlockedByViewer() {
        return mBlockedByViewer;
    }

    public void setBlockedByViewer(Boolean blockedByViewer) {
        mBlockedByViewer = blockedByViewer;
    }

    public Boolean getFollowedByViewer() {
        return mFollowedByViewer;
    }

    public void setFollowedByViewer(Boolean followedByViewer) {
        mFollowedByViewer = followedByViewer;
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

    public Boolean getIsUnpublished() {
        return mIsUnpublished;
    }

    public void setIsUnpublished(Boolean isUnpublished) {
        mIsUnpublished = isUnpublished;
    }

    public Boolean getIsVerified() {
        return mIsVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        mIsVerified = isVerified;
    }

    public String getProfilePicUrl() {
        return mProfilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        mProfilePicUrl = profilePicUrl;
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
