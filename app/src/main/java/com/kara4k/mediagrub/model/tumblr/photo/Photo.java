
package com.kara4k.mediagrub.model.tumblr.photo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("alt_sizes")
    private List<AltSize> mAltSizes;
    @SerializedName("caption")
    private String mCaption;
    @SerializedName("original_size")
    private OriginalSize mOriginalSize;

    public List<AltSize> getAltSizes() {
        return mAltSizes;
    }

    public void setAltSizes(List<AltSize> altSizes) {
        mAltSizes = altSizes;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public OriginalSize getOriginalSize() {
        return mOriginalSize;
    }

    public void setOriginalSize(OriginalSize originalSize) {
        mOriginalSize = originalSize;
    }

}
