package com.kara4k.mediagrub.other;


import android.app.Application;

import com.kara4k.mediagrub.di.AppComponent;
import com.kara4k.mediagrub.di.DaggerAppComponent;
import com.kara4k.mediagrub.di.modules.AppModule;
import com.vk.sdk.VKSdk;

public class App extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponents();
        VKSdk.initialize(this);
    }

    private void initComponents() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
