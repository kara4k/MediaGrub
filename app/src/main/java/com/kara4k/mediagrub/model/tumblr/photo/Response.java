
package com.kara4k.mediagrub.model.tumblr.photo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("posts")
    private List<Post> mPosts;
    @SerializedName("total_posts")
    private Long mTotalPosts;

    public List<Post> getPosts() {
        return mPosts;
    }

    public void setPosts(List<Post> posts) {
        mPosts = posts;
    }

    public Long getTotalPosts() {
        return mTotalPosts;
    }

    public void setTotalPosts(Long totalPosts) {
        mTotalPosts = totalPosts;
    }

}
