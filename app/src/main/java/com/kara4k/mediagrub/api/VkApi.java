package com.kara4k.mediagrub.api;


import com.kara4k.mediagrub.model.vk.custom.group.CustomGroupResponse;
import com.kara4k.mediagrub.model.vk.custom.users.UsersResponse;
import com.kara4k.mediagrub.model.vk.friends.FriendsResponse;
import com.kara4k.mediagrub.model.vk.groups.GroupsResponse;
import com.kara4k.mediagrub.model.vk.photo.PhotoResponse;
import com.kara4k.mediagrub.model.vk.photoalbum.PhotoAlbumResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApi {

    String SERVICE_KEY = "76a6d2c876a6d2c876a6d2c8cd76c672cf776a676a6d2c82cfd74df80f524f4d0422320";

    @GET("friends.get?v=5.52&fields=first_name,photo_100")
    Observable<FriendsResponse> getFriends(
            @Query("access_token") String token);

    @GET("groups.get?v=5.52&extended=1")
    Observable<GroupsResponse> getGroups(
            @Query("access_token") String token);

    @GET("photos.getAlbums?v=5.52&need_system=1&need_covers=1")
    Observable<PhotoAlbumResponse> getPhotoAlbums(
            @Query("owner_id") String ownerId,
            @Query("access_token") String token);

    @GET("photos.get?v=5.52&count=1000&rev=1")
    Observable<PhotoResponse> getPhotos(@Query("owner_id") String ownerId,
                                        @Query("album_id") String albumId,
                                        @Query("access_token") String token);

    @GET("photos.get?v=5.52&count=1000")
    Observable<PhotoResponse> getPhotos(@Query("owner_id") String ownerId,
                                        @Query("album_id") String albumId,
                                        @Query("offset") int offset,
                                        @Query("access_token") String token);

    @GET("photos.search?v=5.52&count=1000&radius=50000")
    Observable<PhotoResponse> searchPhotos(@Query("q") String query,
                                        @Query("access_token") String token);

    @GET("photos.search?v=5.52&count=1000&radius=50000")
    Observable<PhotoResponse> searchPhotos(@Query("q") String query,
                                        @Query("offset") int offset,
                                        @Query("access_token") String token);

    @GET("users.get?v=5.89&fields=photo_100")
    Observable<UsersResponse> getUsersInfo(
            @Query("user_ids") String userIds,
            @Query("access_token") String token);

    @GET("groups.getById?v=5.89")
    Observable<CustomGroupResponse> getGroupsInfo(
            @Query("group_ids") String groupIds,
            @Query("access_token") String token);

}
