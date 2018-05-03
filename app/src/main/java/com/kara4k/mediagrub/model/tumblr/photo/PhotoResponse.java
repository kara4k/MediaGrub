
package com.kara4k.mediagrub.model.tumblr.photo;

import com.google.gson.annotations.SerializedName;

public class PhotoResponse {

    @SerializedName("meta")
    private Meta mMeta;
    @SerializedName("response")
    private Response mResponse;

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

}
