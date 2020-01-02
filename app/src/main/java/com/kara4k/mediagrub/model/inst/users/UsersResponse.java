
package com.kara4k.mediagrub.model.inst.users;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UsersResponse {

    @SerializedName("clear_client_cache")
    private Boolean mClearClientCache;
    @SerializedName("has_more")
    private Boolean mHasMore;
    @SerializedName("hashtags")
    private List<Object> mHashtags;
    @SerializedName("places")
    private List<Object> mPlaces;
    @SerializedName("rank_token")
    private String mRankToken;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("users")
    private List<User> mUsers;

    public Boolean getClearClientCache() {
        return mClearClientCache;
    }

    public void setClearClientCache(Boolean clearClientCache) {
        mClearClientCache = clearClientCache;
    }

    public Boolean getHasMore() {
        return mHasMore;
    }

    public void setHasMore(Boolean hasMore) {
        mHasMore = hasMore;
    }

    public List<Object> getHashtags() {
        return mHashtags;
    }

    public void setHashtags(List<Object> hashtags) {
        mHashtags = hashtags;
    }

    public List<Object> getPlaces() {
        return mPlaces;
    }

    public void setPlaces(List<Object> places) {
        mPlaces = places;
    }

    public String getRankToken() {
        return mRankToken;
    }

    public void setRankToken(String rankToken) {
        mRankToken = rankToken;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

}
