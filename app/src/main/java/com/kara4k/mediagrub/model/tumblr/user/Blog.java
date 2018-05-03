
package com.kara4k.mediagrub.model.tumblr.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Blog {

    @SerializedName("ask")
    private Boolean mAsk;
    @SerializedName("ask_anon")
    private Boolean mAskAnon;
    @SerializedName("ask_page_title")
    private String mAskPageTitle;
    @SerializedName("can_subscribe")
    private Boolean mCanSubscribe;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("is_adult")
    private Boolean mIsAdult;
    @SerializedName("is_nsfw")
    private Boolean mIsNsfw;
    @SerializedName("is_optout_ads")
    private Boolean mIsOptoutAds;
    @SerializedName("name")
    private String mName;
    @SerializedName("posts")
    private Long mPosts;
    @SerializedName("reply_conditions")
    private String mReplyConditions;
    @SerializedName("share_likes")
    private Boolean mShareLikes;
    @SerializedName("submission_page_title")
    private String mSubmissionPageTitle;
    @SerializedName("subscribed")
    private Boolean mSubscribed;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("total_posts")
    private Long mTotalPosts;
    @SerializedName("updated")
    private Long mUpdated;
    @SerializedName("url")
    private String mUrl;

    public Boolean getAsk() {
        return mAsk;
    }

    public void setAsk(Boolean ask) {
        mAsk = ask;
    }

    public Boolean getAskAnon() {
        return mAskAnon;
    }

    public void setAskAnon(Boolean askAnon) {
        mAskAnon = askAnon;
    }

    public String getAskPageTitle() {
        return mAskPageTitle;
    }

    public void setAskPageTitle(String askPageTitle) {
        mAskPageTitle = askPageTitle;
    }

    public Boolean getCanSubscribe() {
        return mCanSubscribe;
    }

    public void setCanSubscribe(Boolean canSubscribe) {
        mCanSubscribe = canSubscribe;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Boolean getIsAdult() {
        return mIsAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        mIsAdult = isAdult;
    }

    public Boolean getIsNsfw() {
        return mIsNsfw;
    }

    public void setIsNsfw(Boolean isNsfw) {
        mIsNsfw = isNsfw;
    }

    public Boolean getIsOptoutAds() {
        return mIsOptoutAds;
    }

    public void setIsOptoutAds(Boolean isOptoutAds) {
        mIsOptoutAds = isOptoutAds;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getPosts() {
        return mPosts;
    }

    public void setPosts(Long posts) {
        mPosts = posts;
    }

    public String getReplyConditions() {
        return mReplyConditions;
    }

    public void setReplyConditions(String replyConditions) {
        mReplyConditions = replyConditions;
    }

    public Boolean getShareLikes() {
        return mShareLikes;
    }

    public void setShareLikes(Boolean shareLikes) {
        mShareLikes = shareLikes;
    }

    public String getSubmissionPageTitle() {
        return mSubmissionPageTitle;
    }

    public void setSubmissionPageTitle(String submissionPageTitle) {
        mSubmissionPageTitle = submissionPageTitle;
    }

    public Boolean getSubscribed() {
        return mSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        mSubscribed = subscribed;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getTotalPosts() {
        return mTotalPosts;
    }

    public void setTotalPosts(Long totalPosts) {
        mTotalPosts = totalPosts;
    }

    public Long getUpdated() {
        return mUpdated;
    }

    public void setUpdated(Long updated) {
        mUpdated = updated;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
