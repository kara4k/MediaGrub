
package com.kara4k.mediagrub.model.twitter.media;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtendedEntities {

    @SerializedName("media")
    @Expose
    private List<Medium__> media = null;

    public List<Medium__> getMedia() {
        return media;
    }

    public void setMedia(List<Medium__> media) {
        this.media = media;
    }

}
