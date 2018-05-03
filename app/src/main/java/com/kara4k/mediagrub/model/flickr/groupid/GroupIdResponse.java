
package com.kara4k.mediagrub.model.flickr.groupid;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GroupIdResponse {

    @SerializedName("group")
    private Group mGroup;
    @SerializedName("stat")
    private String mStat;

    public Group getGroup() {
        return mGroup;
    }

    public void setGroup(Group group) {
        mGroup = group;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }

}
