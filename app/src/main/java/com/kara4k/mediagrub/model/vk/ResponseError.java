
package com.kara4k.mediagrub.model.vk;

import com.google.gson.annotations.SerializedName;

public class ResponseError {

    @SerializedName("error_code")
    private Long mErrorCode;
    @SerializedName("error_msg")
    private String mErrorMsg;

    public Long getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(Long errorCode) {
        mErrorCode = errorCode;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        mErrorMsg = errorMsg;
    }

}
