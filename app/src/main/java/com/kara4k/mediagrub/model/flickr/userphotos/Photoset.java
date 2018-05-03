
package com.kara4k.mediagrub.model.flickr.userphotos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photoset {

    @SerializedName("id")
    private String mId;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("ownername")
    private String mOwnername;
    @SerializedName("page")
    private Integer mPage;
    @SerializedName("pages")
    private Integer mPages;
    @SerializedName("per_page")
    private Long mPerPage;
    @SerializedName("perpage")
    private Long mPerpage;
    @SerializedName("photo")
    private List<Photo> mPhoto;
    @SerializedName("primary")
    private String mPrimary;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("total")
    private Long mTotal;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public String getOwnername() {
        return mOwnername;
    }

    public void setOwnername(String ownername) {
        mOwnername = ownername;
    }

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }

    public Integer getPages() {
        return mPages;
    }

    public void setPages(Integer pages) {
        mPages = pages;
    }

    public Long getPerPage() {
        return mPerPage;
    }

    public void setPerPage(Long perPage) {
        mPerPage = perPage;
    }

    public Long getPerpage() {
        return mPerpage;
    }

    public void setPerpage(Long perpage) {
        mPerpage = perpage;
    }

    public List<Photo> getPhoto() {
        return mPhoto;
    }

    public void setPhoto(List<Photo> photo) {
        mPhoto = photo;
    }

    public String getPrimary() {
        return mPrimary;
    }

    public void setPrimary(String primary) {
        mPrimary = primary;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
