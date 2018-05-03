
package com.kara4k.mediagrub.model.flickr.id;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String mId;
    @SerializedName("username")
    private Username mUsername;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Username getUsername() {
        return mUsername;
    }

    public void setUsername(Username username) {
        mUsername = username;
    }

}
