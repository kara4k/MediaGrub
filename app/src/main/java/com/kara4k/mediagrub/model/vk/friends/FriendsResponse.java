
package com.kara4k.mediagrub.model.vk.friends;

import com.google.gson.annotations.SerializedName;

public class FriendsResponse {

    @SerializedName("response")
    private Response mResponse;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

}
