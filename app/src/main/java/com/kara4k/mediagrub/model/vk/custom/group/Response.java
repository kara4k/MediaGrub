
package com.kara4k.mediagrub.model.vk.custom.group;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Response {

    @SerializedName("id")
    private Long mId;
    @SerializedName("is_closed")
    private Long mIsClosed;
    @SerializedName("name")
    private String mName;
    @SerializedName("photo_100")
    private String mPhoto100;
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
