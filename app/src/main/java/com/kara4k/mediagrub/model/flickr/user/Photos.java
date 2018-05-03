
package com.kara4k.mediagrub.model.flickr.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Photos {

    @SerializedName("count")
    private Count mCount;
    @SerializedName("firstdate")
    private Firstdate mFirstdate;
    @SerializedName("firstdatetaken")
    private Firstdatetaken mFirstdatetaken;

    public Count getCount() {
        return mCount;
    }

    public void setCount(Count count) {
        mCount = count;
    }

    public Firstdate getFirstdate() {
        return mFirstdate;
    }

    public void setFirstdate(Firstdate firstdate) {
        mFirstdate = firstdate;
    }

    public Firstdatetaken getFirstdatetaken() {
        return mFirstdatetaken;
    }

    public void setFirstdatetaken(Firstdatetaken firstdatetaken) {
        mFirstdatetaken = firstdatetaken;
    }

}
