package com.kara4k.mediagrub.api;


import com.kara4k.mediagrub.model.twitter.media.Tweet;
import com.kara4k.mediagrub.model.twitter.users.User;
import com.kara4k.mediagrub.model.twitter.users.search.UserSearchResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TwitterApi {

    String BEARER = "AAAAAAAAAAAAAAAAAAAAAPPe4wAAAAAAtzPCt3CZ0En2bUaGdCBtFkUTUKk%3DLx6yNW5acGJlZBgBzGdTxvTqln8nYgfnOWJbvscMZ0m808T43A";
    String BASE_USER_SEARCH_URL = "https://twitter.com/search?f=users&lang=en&q=";

    @Headers({
            "Host: api.twitter.com",
            "Authorization: Bearer " + BEARER,
            "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"
    })
    @GET("users/lookup.json?")
    Observable<List<User>> getUsersInfo(@Query("screen_name") String screenName);

    @Headers({
            "Host: api.twitter.com",
            "Authorization: Bearer " + BEARER,
            "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"
    })
    @GET("statuses/user_timeline.json?count=200&trim_user=true&exclude_replies=false")
    Observable<List<Tweet>> getUserTweets(@Query("screen_name") String screenName);

    @Headers({
            "Host: api.twitter.com",
            "Authorization: Bearer " + BEARER,
            "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"
    })
    @GET("statuses/user_timeline.json?count=200&trim_user=true&exclude_replies=false")
    Observable<List<Tweet>> getUserTweets(@Query("screen_name") String screenName,
                                          @Query("max_id") String maxId);

    @GET()
    @Headers({
            "x-push-state-request: true"
    })
    Single<UserSearchResponse> searchUsers(@Url String url);

}
