
package com.kara4k.mediagrub.model.vk.users;

import com.google.gson.annotations.SerializedName;
import com.kara4k.mediagrub.model.vk.ResponseError;

import java.util.List;

public class UsersResponse {

    @SerializedName("response")
    private List<Response> mResponse;
    @SerializedName("error")
    private ResponseError mResponseError;

    public List<Response> getResponse() {
        return mResponse;
    }

    public void setResponse(List<Response> response) {
        mResponse = response;
    }

    public ResponseError getResponseError() {
        return mResponseError;
    }

    public void setResponseError(ResponseError responseError) {
        mResponseError = responseError;
    }
}
