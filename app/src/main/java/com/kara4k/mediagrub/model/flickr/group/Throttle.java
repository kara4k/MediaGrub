
package com.kara4k.mediagrub.model.flickr.group;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Throttle {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("mode")
    private String mMode;
    @SerializedName("remaining")
    private Long mRemaining;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public String getMode() {
        return mMode;
    }

    public void setMode(String mode) {
        mMode = mode;
    }

    public Long getRemaining() {
        return mRemaining;
    }

    public void setRemaining(Long remaining) {
        mRemaining = remaining;
    }

}
