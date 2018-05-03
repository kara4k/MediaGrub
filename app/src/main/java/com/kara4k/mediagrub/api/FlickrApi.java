package com.kara4k.mediagrub.api;


import com.kara4k.mediagrub.model.flickr.group.GroupResponse;
import com.kara4k.mediagrub.model.flickr.groupid.GroupIdResponse;
import com.kara4k.mediagrub.model.flickr.groupphotos.GroupPhotosResponse;
import com.kara4k.mediagrub.model.flickr.id.UserIdResponse;
import com.kara4k.mediagrub.model.flickr.user.UserResponse;
import com.kara4k.mediagrub.model.flickr.useralbum.UserAlbumsResponse;
import com.kara4k.mediagrub.model.flickr.userphotos.UserPhotosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {

    String SERVICE_KEY = "";
    String DEFAULT_VALUES = "?format=json&nojsoncallback=1&api_key=" + SERVICE_KEY;

    @GET(DEFAULT_VALUES + "&method=flickr.urls.lookupUser")
    Observable<UserIdResponse> getUserId(@Query("url") String url);

    @GET(DEFAULT_VALUES + "&method=flickr.people.getInfo")
    Observable<UserResponse> getUserInfo(@Query("user_id") String id);

    @GET(DEFAULT_VALUES + "&method=flickr.urls.lookupGroup")
    Observable<GroupIdResponse> getGroupId(@Query("url") String url);

    @GET(DEFAULT_VALUES + "&method=flickr.groups.getInfo")
    Observable<GroupResponse> getGroupInfo(@Query("group_id") String id);

    @GET(DEFAULT_VALUES + "&method=flickr.photosets.getList&primary_photo_extras=url_s,url_m,url_o&per_page=500")
    Observable<UserAlbumsResponse> getUserAlbums(@Query("user_id") String id);

    @GET(DEFAULT_VALUES + "&method=flickr.photosets.getPhotos&per_page=500&extras=url_n,url_m,url_-,url_z,url_c,url_b,url_h,url_k,url_o")
    Observable<UserPhotosResponse> getAlbumPhotos(
            @Query("user_id") String userId,
            @Query("photoset_id") String photosetId
    );

    @GET(DEFAULT_VALUES + "&method=flickr.photosets.getPhotos&per_page=500&extras=url_n,url_m,url_-,url_z,url_c,url_b,url_h,url_k,url_o")
    Observable<UserPhotosResponse> getAlbumPhotos(
            @Query("user_id") String userId,
            @Query("photoset_id") String photosetId,
            @Query("page") int page
    );

    @GET(DEFAULT_VALUES + "&method=flickr.groups.pools.getPhotos&per_page=500&extras=url_n,url_m,url_-,url_z,url_c,url_b,url_h,url_k,url_o")
    Observable<GroupPhotosResponse> getGroupPhotos(
            @Query("group_id") String groupId,
            @Query("page") int page
    );

    @GET(DEFAULT_VALUES + "&method=flickr.photos.search&media=photos&per_page=500&extras=url_n,url_m,url_-,url_z,url_c,url_b,url_h,url_k,url_o")
    Observable<GroupPhotosResponse> searchPhotos(
            @Query("text") String text,
            @Query("page") int page
    );

}
