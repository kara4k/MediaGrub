
package com.kara4k.mediagrub.model.inst.detailed;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DashInfo {

    @SerializedName("is_dash_eligible")
    private Boolean mIsDashEligible;
    @SerializedName("number_of_qualities")
    private Long mNumberOfQualities;
    @SerializedName("video_dash_manifest")
    private Object mVideoDashManifest;

    public Boolean getIsDashEligible() {
        return mIsDashEligible;
    }

    public void setIsDashEligible(Boolean isDashEligible) {
        mIsDashEligible = isDashEligible;
    }

    public Long getNumberOfQualities() {
        return mNumberOfQualities;
    }

    public void setNumberOfQualities(Long numberOfQualities) {
        mNumberOfQualities = numberOfQualities;
    }

    public Object getVideoDashManifest() {
        return mVideoDashManifest;
    }

    public void setVideoDashManifest(Object videoDashManifest) {
        mVideoDashManifest = videoDashManifest;
    }

}
