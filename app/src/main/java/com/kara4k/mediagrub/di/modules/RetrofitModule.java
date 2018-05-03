package com.kara4k.mediagrub.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.api.TumblrApi;
import com.kara4k.mediagrub.api.TwitterApi;
import com.kara4k.mediagrub.api.VkApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    public static final String VK = "vkontakte";
    public static final String INSTAGRAM = "instagram";
    public static final String TWITTER = "twitter";
    public static final String TUMBLR = "tumblr";
    public static final String FLICKR = "flickr";

    private static final String VK_ENDPOINT = "https://api.vk.com/method/";
    private static final String INSTAGRAM_ENDPOINT = "https://www.instagram.com/";
    private static final String TWITTER_ENDPOINT = "https://api.twitter.com/1.1/";
    private static final String TUMBLR_ENDPOINT = "https://api.tumblr.com/v2/blog/";
    private static final String FLICKR_ENDPOINT = "https://api.flickr.com/services/rest/";

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @NonNull
    private Retrofit provideRetrofit(OkHttpClient client, String endpoint) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    @Named(VK)
    Retrofit provideVkRetrofit(OkHttpClient client) {
        return provideRetrofit(client, VK_ENDPOINT);
    }

    @Provides
    @Singleton
    VkApi provideVkApi(@Named(VK) Retrofit retrofit) {
        return retrofit.create(VkApi.class);
    }

    @Singleton
    @Provides
    @Named(INSTAGRAM)
    Retrofit provideInstagramRetrofit(OkHttpClient client) {
        return provideRetrofit(client, INSTAGRAM_ENDPOINT);
    }

    @Provides
    @Singleton
    InstApi provideInstagramApi(@Named(INSTAGRAM) Retrofit retrofit) {
        return retrofit.create(InstApi.class);
    }

    @Singleton
    @Provides
    @Named(TWITTER)
    Retrofit provideTwitterRetrofit(OkHttpClient client) {
        return provideRetrofit(client, TWITTER_ENDPOINT);
    }

    @Provides
    @Singleton
    TwitterApi provideTwitterApi(@Named(TWITTER) Retrofit retrofit) {
        return retrofit.create(TwitterApi.class);
    }

    @Singleton
    @Provides
    @Named(TUMBLR)
    Retrofit provideTumblrRetrofit(OkHttpClient client) {
        return provideRetrofit(client, TUMBLR_ENDPOINT);
    }

    @Provides
    @Singleton
    TumblrApi provideTumblrApi(@Named(TUMBLR) Retrofit retrofit) {
        return retrofit.create(TumblrApi.class);
    }

    @Singleton
    @Provides
    @Named(FLICKR)
    Retrofit provideFlickrRetrofit(OkHttpClient client) {
        return provideRetrofit(client, FLICKR_ENDPOINT);
    }

    @Provides
    @Singleton
    FlickrApi provideFlickrApi(@Named(FLICKR) Retrofit retrofit) {
        return retrofit.create(FlickrApi.class);
    }

}
