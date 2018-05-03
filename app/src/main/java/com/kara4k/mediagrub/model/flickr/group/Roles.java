
package com.kara4k.mediagrub.model.flickr.group;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Roles {

    @SerializedName("admin")
    private String mAdmin;
    @SerializedName("member")
    private String mMember;
    @SerializedName("moderator")
    private String mModerator;

    public String getAdmin() {
        return mAdmin;
    }

    public void setAdmin(String admin) {
        mAdmin = admin;
    }

    public String getMember() {
        return mMember;
    }

    public void setMember(String member) {
        mMember = member;
    }

    public String getModerator() {
        return mModerator;
    }

    public void setModerator(String moderator) {
        mModerator = moderator;
    }

}
