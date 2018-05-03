
package com.kara4k.mediagrub.model.flickr.useralbum;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Photosets {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("pages")
    private Long mPages;
    @SerializedName("perpage")
    private Long mPerpage;
    @SerializedName("photoset")
    private List<Photoset> mPhotoset;
    @SerializedName("total")
    private Long mTotal;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getPages() {
        return mPages;
    }

    public void setPages(Long pages) {
        mPages = pages;
    }

    public Long getPerpage() {
        return mPerpage;
    }

    public void setPerpage(Long perpage) {
        mPerpage = perpage;
    }

    public List<Photoset> getPhotoset() {
        return mPhotoset;
    }

    public void setPhotoset(List<Photoset> photoset) {
        mPhotoset = photoset;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
