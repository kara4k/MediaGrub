
package com.kara4k.mediagrub.model.twitter.media;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoInfo {

    @SerializedName("aspect_ratio")
    @Expose
    private List<Integer> aspectRatio = null;
    @SerializedName("variants")
    @Expose
    private List<Variant> variants = null;

    public List<Integer> getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(List<Integer> aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

}
