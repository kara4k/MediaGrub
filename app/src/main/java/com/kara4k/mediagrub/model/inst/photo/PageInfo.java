
package com.kara4k.mediagrub.model.inst.photo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PageInfo {

    @SerializedName("end_cursor")
    private String mEndCursor;
    @SerializedName("has_next_page")
    private Boolean mHasNextPage;

    public String getEndCursor() {
        return mEndCursor;
    }

    public void setEndCursor(String endCursor) {
        mEndCursor = endCursor;
    }

    public Boolean getHasNextPage() {
        return mHasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        mHasNextPage = hasNextPage;
    }

}
