
package com.kara4k.mediagrub.model.flickr.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Timezone {

    @SerializedName("label")
    private String mLabel;
    @SerializedName("offset")
    private String mOffset;
    @SerializedName("timezone_id")
    private String mTimezoneId;

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getOffset() {
        return mOffset;
    }

    public void setOffset(String offset) {
        mOffset = offset;
    }

    public String getTimezoneId() {
        return mTimezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        mTimezoneId = timezoneId;
    }

}
