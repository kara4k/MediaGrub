
package com.kara4k.mediagrub.model.inst.photo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class User {

    @SerializedName("edge_owner_to_timeline_media")
    private EdgeOwnerToTimelineMedia mEdgeOwnerToTimelineMedia;

    public EdgeOwnerToTimelineMedia getEdgeOwnerToTimelineMedia() {
        return mEdgeOwnerToTimelineMedia;
    }

    public void setEdgeOwnerToTimelineMedia(EdgeOwnerToTimelineMedia edgeOwnerToTimelineMedia) {
        mEdgeOwnerToTimelineMedia = edgeOwnerToTimelineMedia;
    }

}
