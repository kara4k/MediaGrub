
package com.kara4k.mediagrub.model.vk.photoalbum;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("items")
    private List<Item> mItems;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

}
