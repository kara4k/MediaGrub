
package com.kara4k.mediagrub.model.vk.videoalbum;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("id")
    private Long mId;
    @SerializedName("owner_id")
    private Long mOwnerId;
    @SerializedName("photo_160")
    private String mPhoto160;
    @SerializedName("photo_320")
    private String mPhoto800;
    @SerializedName("photo_800")
    private String mPhoto320;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_time")
    private Long mUpdatedTime;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
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

    public String getPhoto160() {
        return mPhoto160;
    }

    public void setPhoto160(String photo160) {
        mPhoto160 = photo160;
    }

    public String getPhoto320() {
        return mPhoto320;
    }

    public void setPhoto320(String photo320) {
        mPhoto320 = photo320;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        mUpdatedTime = updatedTime;
    }

    public String getPhoto800() {
        return mPhoto800;
    }

    public void setPhoto800(String photo800) {
        mPhoto800 = photo800;
    }
}
