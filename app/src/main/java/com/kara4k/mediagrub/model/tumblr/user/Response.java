
package com.kara4k.mediagrub.model.tumblr.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Response {

    @SerializedName("blog")
    private Blog mBlog;

    public Blog getBlog() {
        return mBlog;
    }

    public void setBlog(Blog blog) {
        mBlog = blog;
    }

}
