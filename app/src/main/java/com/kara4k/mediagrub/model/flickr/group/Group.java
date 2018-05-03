
package com.kara4k.mediagrub.model.flickr.group;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Group {

    @SerializedName("blast")
    private Blast mBlast;
    @SerializedName("description")
    private Description mDescription;
    @SerializedName("iconfarm")
    private Long mIconfarm;
    @SerializedName("iconserver")
    private String mIconserver;
    @SerializedName("id")
    private String mId;
    @SerializedName("ispoolmoderated")
    private Long mIspoolmoderated;
    @SerializedName("lang")
    private Object mLang;
    @SerializedName("members")
    private Members mMembers;
    @SerializedName("name")
    private Name mName;
    @SerializedName("nsid")
    private String mNsid;
    @SerializedName("path_alias")
    private String mPathAlias;
    @SerializedName("photo_limit_opt_out")
    private String mPhotoLimitOptOut;
    @SerializedName("pool_count")
    private PoolCount mPoolCount;
    @SerializedName("privacy")
    private Privacy mPrivacy;
    @SerializedName("restrictions")
    private Restrictions mRestrictions;
    @SerializedName("roles")
    private Roles mRoles;
    @SerializedName("rules")
    private Rules mRules;
    @SerializedName("throttle")
    private Throttle mThrottle;
    @SerializedName("topic_count")
    private TopicCount mTopicCount;

    public Blast getBlast() {
        return mBlast;
    }

    public void setBlast(Blast blast) {
        mBlast = blast;
    }

    public Description getDescription() {
        return mDescription;
    }

    public void setDescription(Description description) {
        mDescription = description;
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

    public Long getIspoolmoderated() {
        return mIspoolmoderated;
    }

    public void setIspoolmoderated(Long ispoolmoderated) {
        mIspoolmoderated = ispoolmoderated;
    }

    public Object getLang() {
        return mLang;
    }

    public void setLang(Object lang) {
        mLang = lang;
    }

    public Members getMembers() {
        return mMembers;
    }

    public void setMembers(Members members) {
        mMembers = members;
    }

    public Name getName() {
        return mName;
    }

    public void setName(Name name) {
        mName = name;
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

    public String getPhotoLimitOptOut() {
        return mPhotoLimitOptOut;
    }

    public void setPhotoLimitOptOut(String photoLimitOptOut) {
        mPhotoLimitOptOut = photoLimitOptOut;
    }

    public PoolCount getPoolCount() {
        return mPoolCount;
    }

    public void setPoolCount(PoolCount poolCount) {
        mPoolCount = poolCount;
    }

    public Privacy getPrivacy() {
        return mPrivacy;
    }

    public void setPrivacy(Privacy privacy) {
        mPrivacy = privacy;
    }

    public Restrictions getRestrictions() {
        return mRestrictions;
    }

    public void setRestrictions(Restrictions restrictions) {
        mRestrictions = restrictions;
    }

    public Roles getRoles() {
        return mRoles;
    }

    public void setRoles(Roles roles) {
        mRoles = roles;
    }

    public Rules getRules() {
        return mRules;
    }

    public void setRules(Rules rules) {
        mRules = rules;
    }

    public Throttle getThrottle() {
        return mThrottle;
    }

    public void setThrottle(Throttle throttle) {
        mThrottle = throttle;
    }

    public TopicCount getTopicCount() {
        return mTopicCount;
    }

    public void setTopicCount(TopicCount topicCount) {
        mTopicCount = topicCount;
    }

}
