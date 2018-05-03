
package com.kara4k.mediagrub.model.flickr.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UserResponse {

    @SerializedName("person")
    private Person mPerson;
    @SerializedName("stat")
    private String mStat;

    public Person getPerson() {
        return mPerson;
    }

    public void setPerson(Person person) {
        mPerson = person;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }

}
