
package com.kara4k.mediagrub.model.inst.users;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class User {

    @SerializedName("can_see_primary_country_in_settings")
    private Boolean mCanSeePrimaryCountryInSettings;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("has_anonymous_profile_picture")
    private Boolean mHasAnonymousProfilePicture;
    @SerializedName("is_private")
    private Boolean mIsPrivate;
    @SerializedName("is_verified")
    private Boolean mIsVerified;
    @SerializedName("latest_reel_media")
    private Long mLatestReelMedia;
    @SerializedName("mutual_followers_count")
    private Long mMutualFollowersCount;
    @SerializedName("pk")
    private String mPk;
    @SerializedName("position")
    private Long mPosition;
    @SerializedName("profile_pic_id")
    private String mProfilePicId;
    @SerializedName("profile_pic_url")
    private String mProfilePicUrl;
    @SerializedName("user")
    private User mUser;
    @SerializedName("username")
    private String mUsername;

    public Boolean getCanSeePrimaryCountryInSettings() {
        return mCanSeePrimaryCountryInSettings;
    }

    public void setCanSeePrimaryCountryInSettings(Boolean canSeePrimaryCountryInSettings) {
        mCanSeePrimaryCountryInSettings = canSeePrimaryCountryInSettings;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public Boolean getHasAnonymousProfilePicture() {
        return mHasAnonymousProfilePicture;
    }

    public void setHasAnonymousProfilePicture(Boolean hasAnonymousProfilePicture) {
        mHasAnonymousProfilePicture = hasAnonymousProfilePicture;
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

    public Long getLatestReelMedia() {
        return mLatestReelMedia;
    }

    public void setLatestReelMedia(Long latestReelMedia) {
        mLatestReelMedia = latestReelMedia;
    }

    public Long getMutualFollowersCount() {
        return mMutualFollowersCount;
    }

    public void setMutualFollowersCount(Long mutualFollowersCount) {
        mMutualFollowersCount = mutualFollowersCount;
    }

    public String getPk() {
        return mPk;
    }

    public void setPk(String pk) {
        mPk = pk;
    }

    public Long getPosition() {
        return mPosition;
    }

    public void setPosition(Long position) {
        mPosition = position;
    }

    public String getProfilePicId() {
        return mProfilePicId;
    }

    public void setProfilePicId(String profilePicId) {
        mProfilePicId = profilePicId;
    }

    public String getProfilePicUrl() {
        return mProfilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        mProfilePicUrl = profilePicUrl;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
