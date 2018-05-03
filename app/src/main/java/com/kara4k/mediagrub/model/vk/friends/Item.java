
package com.kara4k.mediagrub.model.vk.friends;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("online")
    private Long mOnline;
    @SerializedName("photo_100")
    private String mPhoto100;

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

    public Long getOnline() {
        return mOnline;
    }

    public void setOnline(Long online) {
        mOnline = online;
    }

    public String getPhoto100() {
        return mPhoto100;
    }

    public void setPhoto100(String photo100) {
        mPhoto100 = photo100;
    }

}
