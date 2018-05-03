
package com.kara4k.mediagrub.model.inst.users;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EdgeMediaCollections {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("edges")
    private List<Object> mEdges;
    @SerializedName("page_info")
    private PageInfo mPageInfo;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public List<Object> getEdges() {
        return mEdges;
    }

    public void setEdges(List<Object> edges) {
        mEdges = edges;
    }

    public PageInfo getPageInfo() {
        return mPageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        mPageInfo = pageInfo;
    }

}
