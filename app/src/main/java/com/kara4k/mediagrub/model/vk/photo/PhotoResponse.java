
package com.kara4k.mediagrub.model.vk.photo;

import com.google.gson.annotations.SerializedName;
import com.kara4k.mediagrub.model.vk.ResponseError;

public class PhotoResponse {

    @SerializedName("response")
    private Response mResponse;
    @SerializedName("error")
    private ResponseError mResponseError;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

    public ResponseError getResponseError() {
        return mResponseError;
    }

    public void setResponseError(ResponseError responseError) {
        mResponseError = responseError;
    }
}
