
package com.kara4k.mediagrub.model.inst.photo;

import com.google.gson.annotations.SerializedName;

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
