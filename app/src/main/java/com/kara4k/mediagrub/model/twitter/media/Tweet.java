
package com.kara4k.mediagrub.model.twitter.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private String idStr;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("extended_entities")
    @Expose
    private ExtendedEntities extendedEntities;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    public void setExtendedEntities(ExtendedEntities extendedEntities) {
        this.extendedEntities = extendedEntities;
    }

}
