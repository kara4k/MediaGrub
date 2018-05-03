
package com.kara4k.mediagrub.model.flickr.useralbum;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Photoset {

    @SerializedName("can_comment")
    private Long mCanComment;
    @SerializedName("count_comments")
    private String mCountComments;
    @SerializedName("count_views")
    private String mCountViews;
    @SerializedName("date_create")
    private String mDateCreate;
    @SerializedName("date_update")
    private String mDateUpdate;
    @SerializedName("description")
    private Description mDescription;
    @SerializedName("farm")
    private Long mFarm;
    @SerializedName("id")
    private String mId;
    @SerializedName("needs_interstitial")
    private Long mNeedsInterstitial;
    @SerializedName("photos")
    private Long mPhotos;
    @SerializedName("primary")
    private String mPrimary;
    @SerializedName("primary_photo_extras")
    private PrimaryPhotoExtras mPrimaryPhotoExtras;
    @SerializedName("secret")
    private String mSecret;
    @SerializedName("server")
    private String mServer;
    @SerializedName("title")
    private Title mTitle;
    @SerializedName("videos")
    private Long mVideos;
    @SerializedName("visibility_can_see_set")
    private Long mVisibilityCanSeeSet;

    public Long getCanComment() {
        return mCanComment;
    }

    public void setCanComment(Long canComment) {
        mCanComment = canComment;
    }

    public String getCountComments() {
        return mCountComments;
    }

    public void setCountComments(String countComments) {
        mCountComments = countComments;
    }

    public String getCountViews() {
        return mCountViews;
    }

    public void setCountViews(String countViews) {
        mCountViews = countViews;
    }

    public String getDateCreate() {
        return mDateCreate;
    }

    public void setDateCreate(String dateCreate) {
        mDateCreate = dateCreate;
    }

    public String getDateUpdate() {
        return mDateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        mDateUpdate = dateUpdate;
    }

    public Description getDescription() {
        return mDescription;
    }

    public void setDescription(Description description) {
        mDescription = description;
    }

    public Long getFarm() {
        return mFarm;
    }

    public void setFarm(Long farm) {
        mFarm = farm;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Long getNeedsInterstitial() {
        return mNeedsInterstitial;
    }

    public void setNeedsInterstitial(Long needsInterstitial) {
        mNeedsInterstitial = needsInterstitial;
    }

    public Long getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Long photos) {
        mPhotos = photos;
    }

    public String getPrimary() {
        return mPrimary;
    }

    public void setPrimary(String primary) {
        mPrimary = primary;
    }

    public PrimaryPhotoExtras getPrimaryPhotoExtras() {
        return mPrimaryPhotoExtras;
    }

    public void setPrimaryPhotoExtras(PrimaryPhotoExtras primaryPhotoExtras) {
        mPrimaryPhotoExtras = primaryPhotoExtras;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        mSecret = secret;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public Title getTitle() {
        return mTitle;
    }

    public void setTitle(Title title) {
        mTitle = title;
    }

    public Long getVideos() {
        return mVideos;
    }

    public void setVideos(Long videos) {
        mVideos = videos;
    }

    public Long getVisibilityCanSeeSet() {
        return mVisibilityCanSeeSet;
    }

    public void setVisibilityCanSeeSet(Long visibilityCanSeeSet) {
        mVisibilityCanSeeSet = visibilityCanSeeSet;
    }

}
