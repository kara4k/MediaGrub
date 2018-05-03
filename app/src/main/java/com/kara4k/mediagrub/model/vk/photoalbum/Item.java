
package com.kara4k.mediagrub.model.vk.photoalbum;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private Long mId;
    @SerializedName("owner_id")
    private Long mOwnerId;
    @SerializedName("size")
    private Long mSize;
    @SerializedName("thumb_id")
    private Long mThumbId;
    @SerializedName("thumb_src")
    private String mThumbSrc;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;

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

    public Long getSize() {
        return mSize;
    }

    public void setSize(Long size) {
        mSize = size;
    }

    public Long getThumbId() {
        return mThumbId;
    }

    public void setThumbId(Long thumbId) {
        mThumbId = thumbId;
    }

    public String getThumbSrc() {
        return mThumbSrc;
    }

    public void setThumbSrc(String thumbSrc) {
        mThumbSrc = thumbSrc;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
