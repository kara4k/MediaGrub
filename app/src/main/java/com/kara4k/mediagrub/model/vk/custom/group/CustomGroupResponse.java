
package com.kara4k.mediagrub.model.vk.custom.group;

import com.google.gson.annotations.SerializedName;
import com.kara4k.mediagrub.model.vk.ResponseError;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CustomGroupResponse {

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
