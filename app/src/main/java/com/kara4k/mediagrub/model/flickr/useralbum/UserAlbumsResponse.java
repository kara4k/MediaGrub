
package com.kara4k.mediagrub.model.flickr.useralbum;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UserAlbumsResponse {

    @SerializedName("photosets")
    private Photosets mPhotosets;
    @SerializedName("stat")
    private String mStat;

    public Photosets getPhotosets() {
        return mPhotosets;
    }

    public void setPhotosets(Photosets photosets) {
        mPhotosets = photosets;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }

}
