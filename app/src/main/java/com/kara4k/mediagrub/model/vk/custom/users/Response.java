
package com.kara4k.mediagrub.model.vk.custom.users;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("photo_100")
    private String mPhoto100;
    @SerializedName("name")
    private String mName;
    @SerializedName("screen_name")
    private String mScreenName;
    @SerializedName("is_closed")
    private Boolean mIsClosed;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPhoto100() {
        return mPhoto100;
    }

    public void setPhoto100(String photo100) {
        mPhoto100 = photo100;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getScreenName() {
        return mScreenName;
    }

    public void setScreenName(String screenName) {
        mScreenName = screenName;
    }

    public Boolean getIsClosed() {
        return mIsClosed;
    }

    public void setIsClosed(final Boolean closed) {
        mIsClosed = closed;
    }
}
