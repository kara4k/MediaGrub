package com.kara4k.mediagrub.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.kara4k.mediagrub.api.FlickrApi;
import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.api.TumblrApi;
import com.kara4k.mediagrub.api.TwitterApi;
import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.di.modules.AppModule;
import com.kara4k.mediagrub.di.modules.RetrofitModule;
import com.kara4k.mediagrub.download.DownloadService;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.main.UserCreatorActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {

    Context shareContext();

    SharedPreferences sharePreferences();

    VkApi shareVkApi();

    InstApi shareInstApi();

    TwitterApi shareTwitterApi();

    TumblrApi shareTumblrApi();

    FlickrApi shareFlickrApi();

    DaoSession shareDaoSession();

    void injectDownloadService(DownloadService service);

    void injectUserCreatorActivity(UserCreatorActivity userCreatorActivity);
}