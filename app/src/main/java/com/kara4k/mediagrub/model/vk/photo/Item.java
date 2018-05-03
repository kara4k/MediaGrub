
package com.kara4k.mediagrub.model.vk.photo;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("album_id")
    private Long mAlbumId;
    @SerializedName("date")
    private Long mDate;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("id")
    private Long mId;
    @SerializedName("owner_id")
    private Long mOwnerId;
    @SerializedName("photo_1280")
    private String mPhoto1280;
    @SerializedName("photo_130")
    private String mPhoto130;
    @SerializedName("photo_2560")
    private String mPhoto2560;
    @SerializedName("photo_604")
    private String mPhoto604;
    @SerializedName("photo_75")
    private String mPhoto75;
    @SerializedName("photo_807")
    private String mPhoto807;
    @SerializedName("text")
    private String mText;
    @SerializedName("width")
    private Long mWidth;

    public Long getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(Long albumId) {
        mAlbumId = albumId;
    }

    public Long getDate() {
        return mDate;
    }

    public void setDate(Long date) {
        mDate = date;
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

    public String getPhoto1280() {
        return mPhoto1280;
    }

    public void setPhoto1280(String photo1280) {
        mPhoto1280 = photo1280;
    }

    public String getPhoto130() {
        return mPhoto130;
    }

    public void setPhoto130(String photo130) {
        mPhoto130 = photo130;
    }

    public String getPhoto2560() {
        return mPhoto2560;
    }

    public void setPhoto2560(String photo2560) {
        mPhoto2560 = photo2560;
    }

    public String getPhoto604() {
        return mPhoto604;
    }

    public void setPhoto604(String photo604) {
        mPhoto604 = photo604;
    }

    public String getPhoto75() {
        return mPhoto75;
    }

    public void setPhoto75(String photo75) {
        mPhoto75 = photo75;
    }

    public String getPhoto807() {
        return mPhoto807;
    }

    public void setPhoto807(String photo807) {
        mPhoto807 = photo807;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
