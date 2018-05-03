
package com.kara4k.mediagrub.model.vk.video;

import com.google.gson.annotations.SerializedName;

public class VideoResponse {

    @SerializedName("response")
    private Response mResponse;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

}
