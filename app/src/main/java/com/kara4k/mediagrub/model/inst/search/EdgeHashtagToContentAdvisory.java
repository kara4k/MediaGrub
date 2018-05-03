
package com.kara4k.mediagrub.model.inst.search;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EdgeHashtagToContentAdvisory {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("edges")
    private List<Edge> mEdges;

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

}
