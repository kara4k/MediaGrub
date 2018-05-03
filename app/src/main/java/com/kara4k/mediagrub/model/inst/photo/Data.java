
package com.kara4k.mediagrub.model.inst.photo;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
