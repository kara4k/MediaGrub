
package com.kara4k.mediagrub.model.flickr.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Person {

    @SerializedName("can_buy_pro")
    private Long mCanBuyPro;
    @SerializedName("description")
    private Description mDescription;
    @SerializedName("expire")
    private String mExpire;
    @SerializedName("has_stats")
    private String mHasStats;
    @SerializedName("iconfarm")
    private Long mIconfarm;
    @SerializedName("iconserver")
    private String mIconserver;
    @SerializedName("id")
    private String mId;
    @SerializedName("ispro")
    private Long mIspro;
    @SerializedName("location")
    private Location mLocation;
    @SerializedName("mobileurl")
    private Mobileurl mMobileurl;
    @SerializedName("nsid")
    private String mNsid;
    @SerializedName("path_alias")
    private String mPathAlias;
    @SerializedName("photos")
    private Photos mPhotos;
    @SerializedName("photosurl")
    private Photosurl mPhotosurl;
    @SerializedName("profileurl")
    private Profileurl mProfileurl;
    @SerializedName("realname")
    private Realname mRealname;
    @SerializedName("timezone")
    private Timezone mTimezone;
    @SerializedName("username")
    private Username mUsername;

    public Long getCanBuyPro() {
        return mCanBuyPro;
    }

    public void setCanBuyPro(Long canBuyPro) {
        mCanBuyPro = canBuyPro;
    }

    public Description getDescription() {
        return mDescription;
    }

    public void setDescription(Description description) {
        mDescription = description;
    }

    public String getExpire() {
        return mExpire;
    }

    public void setExpire(String expire) {
        mExpire = expire;
    }

    public String getHasStats() {
        return mHasStats;
    }

    public void setHasStats(String hasStats) {
        mHasStats = hasStats;
    }

    public Long getIconfarm() {
        return mIconfarm;
    }

    public void setIconfarm(Long iconfarm) {
        mIconfarm = iconfarm;
    }

    public String getIconserver() {
        return mIconserver;
    }

    public void setIconserver(String iconserver) {
        mIconserver = iconserver;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Long getIspro() {
        return mIspro;
    }

    public void setIspro(Long ispro) {
        mIspro = ispro;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public Mobileurl getMobileurl() {
        return mMobileurl;
    }

    public void setMobileurl(Mobileurl mobileurl) {
        mMobileurl = mobileurl;
    }

    public String getNsid() {
        return mNsid;
    }

    public void setNsid(String nsid) {
        mNsid = nsid;
    }

    public String getPathAlias() {
        return mPathAlias;
    }

    public void setPathAlias(String pathAlias) {
        mPathAlias = pathAlias;
    }

    public Photos getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photos photos) {
        mPhotos = photos;
    }

    public Photosurl getPhotosurl() {
        return mPhotosurl;
    }

    public void setPhotosurl(Photosurl photosurl) {
        mPhotosurl = photosurl;
    }

    public Profileurl getProfileurl() {
        return mProfileurl;
    }

    public void setProfileurl(Profileurl profileurl) {
        mProfileurl = profileurl;
    }

    public Realname getRealname() {
        return mRealname;
    }

    public void setRealname(Realname realname) {
        mRealname = realname;
    }

    public Timezone getTimezone() {
        return mTimezone;
    }

    public void setTimezone(Timezone timezone) {
        mTimezone = timezone;
    }

    public Username getUsername() {
        return mUsername;
    }

    public void setUsername(Username username) {
        mUsername = username;
    }

}
