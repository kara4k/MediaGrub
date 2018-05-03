
package com.kara4k.mediagrub.model.vk.groups;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private Long mId;
    @SerializedName("is_closed")
    private Long mIsClosed;
    @SerializedName("name")
    private String mName;
    @SerializedName("photo_100")
    private String mPhoto100;
    @SerializedName("photo_200")
    private String mPhoto200;
    @SerializedName("photo_50")
    private String mPhoto50;
    @SerializedName("screen_name")
    private String mScreenName;
    @SerializedName("type")
    private String mType;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getIsClosed() {
        return mIsClosed;
    }

    public void setIsClosed(Long isClosed) {
        mIsClosed = isClosed;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoto100() {
        return mPhoto100;
    }

    public void setPhoto100(String photo100) {
        mPhoto100 = photo100;
    }

    public String getPhoto200() {
        return mPhoto200;
    }

    public void setPhoto200(String photo200) {
        mPhoto200 = photo200;
    }

    public String getPhoto50() {
        return mPhoto50;
    }

    public void setPhoto50(String photo50) {
        mPhoto50 = photo50;
    }

    public String getScreenName() {
        return mScreenName;
    }

    public void setScreenName(String screenName) {
        mScreenName = screenName;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
