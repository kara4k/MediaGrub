
package com.kara4k.mediagrub.model.flickr.groupphotos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("pages")
    private int mPages;
    @SerializedName("perpage")
    private Long mPerpage;
    @SerializedName("photo")
    private List<Photo> mPhoto;
    @SerializedName("total")
    private String mTotal;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public int getPages() {
        return mPages;
    }

    public void setPages(int pages) {
        mPages = pages;
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

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        mTotal = total;
    }

}
