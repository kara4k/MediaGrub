
package com.kara4k.mediagrub.model.inst.photo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EdgeMediaToCaption {

    @SerializedName("edges")
    private List<Edge> mEdges;

    public List<Edge> getEdges() {
        return mEdges;
    }

    public void setEdges(List<Edge> edges) {
        mEdges = edges;
    }

}
