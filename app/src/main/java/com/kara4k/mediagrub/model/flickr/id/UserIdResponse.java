
package com.kara4k.mediagrub.model.flickr.id;

import com.google.gson.annotations.SerializedName;

public class UserIdResponse {

    @SerializedName("stat")
    private String mStat;
    @SerializedName("user")
    private User mUser;

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
