
package com.kara4k.mediagrub.model.flickr.groupid;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Group {

    @SerializedName("groupname")
    private Groupname mGroupname;
    @SerializedName("id")
    private String mId;

    public Groupname getGroupname() {
        return mGroupname;
    }

    public void setGroupname(Groupname groupname) {
        mGroupname = groupname;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

}
