
package com.kara4k.mediagrub.model.twitter.media;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hashtag {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("indices")
    @Expose
    private List<Integer> indices = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

}
