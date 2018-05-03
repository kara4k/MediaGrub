package com.kara4k.mediagrub.view.adapters.recycler;


import java.io.Serializable;

public class AlbumItem extends SelectableItem implements Serializable {

    public static final long serialVersionUID = 19L;
    public static final int UNDEFINED = -1;

    private String mId;
    private String mOwnerId;
    private String mCoverUrl;
    private String mTitle;
    private String mDescription;
    private String mSize;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(String ownerId) {
        mOwnerId = ownerId;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        mCoverUrl = coverUrl;
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

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }

    public int getTotalSize() {
        try {
            return Integer.parseInt(mSize);
        } catch (NumberFormatException e) {
            return UNDEFINED;
        }
    }
}
