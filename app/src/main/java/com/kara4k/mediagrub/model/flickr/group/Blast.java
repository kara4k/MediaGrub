
package com.kara4k.mediagrub.model.flickr.group;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Blast {

    @SerializedName("date_blast_added")
    private String mDateBlastAdded;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("_content")
    private String m_content;

    public String getDateBlastAdded() {
        return mDateBlastAdded;
    }

    public void setDateBlastAdded(String dateBlastAdded) {
        mDateBlastAdded = dateBlastAdded;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String get_content() {
        return m_content;
    }

    public void set_content(String _content) {
        m_content = _content;
    }

}
