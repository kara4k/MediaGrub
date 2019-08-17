
package com.kara4k.mediagrub.model.inst.detailed;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Edge {

    @SerializedName("node")
    private Node mNode;

    public Node getNode() {
        return mNode;
    }

    public void setNode(Node node) {
        mNode = node;
    }

}
