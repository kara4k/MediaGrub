package com.kara4k.mediagrub.model.vk.groups;

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

    public void setCount(final Long count) {
        mCount = count;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(final List<Item> items) {
        mItems = items;
    }
}
