
package com.kara4k.mediagrub.model.inst.photo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EdgeOwnerToTimelineMedia {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("edges")
    private List<Edge> mEdges;
    @SerializedName("page_info")
    private PageInfo mPageInfo;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public List<Edge> getEdges() {
        return mEdges;
    }

    public void setEdges(List<Edge> edges) {
        mEdges = edges;
    }

    public PageInfo getPageInfo() {
        return mPageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        mPageInfo = pageInfo;
    }

}
