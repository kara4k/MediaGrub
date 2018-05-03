package com.kara4k.mediagrub.api;


import com.kara4k.mediagrub.model.tumblr.photo.PhotoResponse;
import com.kara4k.mediagrub.model.tumblr.user.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TumblrApi {

    public static final String SERVICE_KEY = "RbK5c5qPZaOCI7iHa3IB33hpHfkPrg7pm2astzyYoiD1SK2ea3";

    @GET("{name}.tumblr.com/info?api_key=" + SERVICE_KEY)
    Observable<UserResponse> getUserInfo(@Path("name") String blogName);

    @GET("{name}.tumblr.com/posts/photo?api_key=" + SERVICE_KEY)
    Observable<PhotoResponse> getPhotos(@Path("name") String blogName);

    @GET("{name}.tumblr.com/posts/photo?api_key=" + SERVICE_KEY )
    Observable<PhotoResponse> getPhotos(
            @Path("name") String blogName,
            @Query("offset") int offset);

}
