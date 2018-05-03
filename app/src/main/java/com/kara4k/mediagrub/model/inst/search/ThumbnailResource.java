
package com.kara4k.mediagrub.model.inst.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ThumbnailResource {

    @SerializedName("config_height")
    private Long mConfigHeight;
    @SerializedName("config_width")
    private Long mConfigWidth;
    @SerializedName("src")
    private String mSrc;

    public Long getConfigHeight() {
        return mConfigHeight;
    }

    public void setConfigHeight(Long configHeight) {
        mConfigHeight = configHeight;
    }

    public Long getConfigWidth() {
        return mConfigWidth;
    }

    public void setConfigWidth(Long configWidth) {
        mConfigWidth = configWidth;
    }

    public String getSrc() {
        return mSrc;
    }

    public void setSrc(String src) {
        mSrc = src;
    }

}
