package com.kara4k.mediagrub.model.database;


import com.kara4k.mediagrub.view.adapters.recycler.SelectableItem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity(nameInDb = "downloads")
public class MediaItem extends SelectableItem implements Serializable {

    public static final long serialVersionUID = 41L;

    public static final int PHOTO = 1;
    public static final int VIDEO = 2;

    @Id
    private Long dbId;
    private Long mTaskId;
    private String mId;
    private String albumId;
    private String ownerId;
    private String mTitle;
    private String mDescription;
    private String mAddition;
    private String mThumbUrl;
    private String mSourceUrl;
    private int mType;

    @Generated(hash = 1389866713)
    public MediaItem(Long dbId, Long mTaskId, String mId, String albumId,
            String ownerId, String mTitle, String mDescription, String mAddition,
            String mThumbUrl, String mSourceUrl, int mType) {
        this.dbId = dbId;
        this.mTaskId = mTaskId;
        this.mId = mId;
        this.albumId = albumId;
        this.ownerId = ownerId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mAddition = mAddition;
        this.mThumbUrl = mThumbUrl;
        this.mSourceUrl = mSourceUrl;
        this.mType = mType;
    }

    @Generated(hash = 1342512921)
    public MediaItem() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getAddition() {
        return mAddition;
    }

    public void setAddition(String addition) {
        mAddition = addition;
    }

    public String getThumbUrl() {
        return mThumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        mThumbUrl = thumbUrl;
    }

    public String getSourceUrl() {
        return mSourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        mSourceUrl = sourceUrl;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public Long getDbId() {
        return this.dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getMId() {
        return this.mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMDescription() {
        return this.mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getMAddition() {
        return this.mAddition;
    }

    public void setMAddition(String mAddition) {
        this.mAddition = mAddition;
    }

    public String getMThumbUrl() {
        return this.mThumbUrl;
    }

    public void setMThumbUrl(String mThumbUrl) {
        this.mThumbUrl = mThumbUrl;
    }

    public String getMSourceUrl() {
        return this.mSourceUrl;
    }

    public void setMSourceUrl(String mSourceUrl) {
        this.mSourceUrl = mSourceUrl;
    }

    public int getMType() {
        return this.mType;
    }

    public void setMType(int mType) {
        this.mType = mType;
    }

    public Long getMTaskId() {
        return this.mTaskId;
    }

    public void setMTaskId(Long mTaskId) {
        this.mTaskId = mTaskId;
    }


}
