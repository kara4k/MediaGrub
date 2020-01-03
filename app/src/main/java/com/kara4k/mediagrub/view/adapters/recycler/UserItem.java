package com.kara4k.mediagrub.view.adapters.recycler;


import java.io.Serializable;

public class UserItem extends SelectableItem implements Serializable {

    public static final long serialVersionUID = 20L;

    public static final String VKONTAKTE = "VK";
    public static final String INSTAGRAM = "Instagram";
    public static final String TWITTER = "Twitter";
    public static final String TUMBLR = "Tumblr";
    public static final String FLICKR = "Flickr";

    private String mMainText;
    private String mAdditionText;
    private String mId;
    private String mPhotoUrl;
    private String mService;
    private boolean isPrivate;

    public void setMainText(final String mainText) {
        mMainText = mainText;
    }

    public void setAdditionText(final String additionText) {
        mAdditionText = additionText;
    }

    public void setId(final String id) {
        mId = id;
    }

    public void setPhotoUrl(final String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public String getMainText() {
        return mMainText;
    }

    public String getAdditionText() {
        return mAdditionText;
    }

    public String getId() {
        return mId;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public String getService() {
        return mService;
    }

    public void setService(final String service) {
        mService = service;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(final boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "mMainText='" + mMainText + '\'' +
                ", mAdditionText='" + mAdditionText + '\'' +
                ", mId='" + mId + '\'' +
                ", mPhotoUrl='" + mPhotoUrl + '\'' +
                ", mService='" + mService + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
