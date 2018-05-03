
package com.kara4k.mediagrub.model.vk.video;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("adding_date")
    private Long mAddingDate;
    @SerializedName("can_add")
    private Long mCanAdd;
    @SerializedName("comments")
    private Long mComments;
    @SerializedName("date")
    private Long mDate;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("duration")
    private Long mDuration;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("id")
    private Long mId;
    @SerializedName("owner_id")
    private Long mOwnerId;
    @SerializedName("photo_130")
    private String mPhoto130;
    @SerializedName("photo_320")
    private String mPhoto320;
    @SerializedName("photo_800")
    private String mPhoto800;
    @SerializedName("platform")
    private String mPlatform;
    @SerializedName("player")
    private String mPlayer;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("views")
    private Long mViews;
    @SerializedName("width")
    private Long mWidth;

    public Long getAddingDate() {
        return mAddingDate;
    }

    public void setAddingDate(Long addingDate) {
        mAddingDate = addingDate;
    }

    public Long getCanAdd() {
        return mCanAdd;
    }

    public void setCanAdd(Long canAdd) {
        mCanAdd = canAdd;
    }

    public Long getComments() {
        return mComments;
    }

    public void setComments(Long comments) {
        mComments = comments;
    }

    public Long getDate() {
        return mDate;
    }

    public void setDate(Long date) {
        mDate = date;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getDuration() {
        return mDuration;
    }

    public void setDuration(Long duration) {
        mDuration = duration;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(Long ownerId) {
        mOwnerId = ownerId;
    }

    public String getPhoto130() {
        return mPhoto130;
    }

    public void setPhoto130(String photo130) {
        mPhoto130 = photo130;
    }

    public String getPhoto320() {
        return mPhoto320;
    }

    public void setPhoto320(String photo320) {
        mPhoto320 = photo320;
    }

    public String getPhoto800() {
        return mPhoto800;
    }

    public void setPhoto800(String photo800) {
        mPhoto800 = photo800;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String platform) {
        mPlatform = platform;
    }

    public String getPlayer() {
        return mPlayer;
    }

    public void setPlayer(String player) {
        mPlayer = player;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getViews() {
        return mViews;
    }

    public void setViews(Long views) {
        mViews = views;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
