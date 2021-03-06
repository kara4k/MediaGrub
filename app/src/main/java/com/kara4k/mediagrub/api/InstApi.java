package com.kara4k.mediagrub.api;


import com.kara4k.mediagrub.model.inst.detailed.DetailedInfo;
import com.kara4k.mediagrub.model.inst.photo.PhotoResponse;
import com.kara4k.mediagrub.model.inst.search.SearchResponse;
import com.kara4k.mediagrub.model.inst.users.UsersResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface InstApi {

    @Headers({
            "Cookie: mid=Wq_2zAAEAAEIRbn1zTbEDdwYCfpz; csrftoken=37fFmpCZiqo3q4g0whDbS463Eqppu8hz; rur=FTW; ig_pr=1; ig_vh=658; ig_or=landscape-primary; ig_vw=234; ",
    })
    @GET("graphql/query/?query_hash=42323d64886122307be10013ad2dcc44")
    Observable<PhotoResponse> getPhotos(@Query("variables") String request);

    @Headers({
            "Cookie: mid=Wq_2zAAEAAEIRbn1zTbEDdwYCfpz; csrftoken=37fFmpCZiqo3q4g0whDbS463Eqppu8hz; rur=FTW; ig_vw=1366; ig_pr=1; ig_vh=658 ",
    })
    @GET("graphql/query/?query_hash=3e7706b09c6184d5eafd8b032dbcf487")
    Observable<SearchResponse> searchPhotos(@Query("variables") String request);

    @GET("graphql/query/?query_hash=477b65a610463740ccdb83135b2014db")
    Call<DetailedInfo> getDetailedIno(@Query("variables") String variables);

    @GET("web/search/topsearch/")
    Observable<UsersResponse> getUserInfo(@Query("query") String username);
}
