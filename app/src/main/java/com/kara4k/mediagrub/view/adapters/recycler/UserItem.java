package com.kara4k.mediagrub.view.adapters.recycler;


import java.io.Serializable;

public class UserItem extends SelectableItem implements Serializable{

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

    public void setMainText(String mainText) {
        mMainText = mainText;
    }

    public void setAdditionText(String additionText) {
        mAdditionText = additionText;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setPhotoUrl(String photoUrl) {
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

    public void setService(String service) {
        mService = service;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "mMainText='" + mMainText + '\'' +
                ", mAdditionText='" + mAdditionText + '\'' +
                ", mId='" + mId + '\'' +
                ", mPhotoUrl='" + mPhotoUrl + '\'' +
                ", mService='" + mService + '\'' +
                '}';
    }
}
