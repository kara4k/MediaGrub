
package com.kara4k.mediagrub.model.inst.detailed;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Data {

    @SerializedName("shortcode_media")
    private ShortcodeMedia mShortcodeMedia;

    public ShortcodeMedia getShortcodeMedia() {
        return mShortcodeMedia;
    }

    public void setShortcodeMedia(ShortcodeMedia shortcodeMedia) {
        mShortcodeMedia = shortcodeMedia;
    }

}
