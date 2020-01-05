package com.kara4k.mediagrub.di;

import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.api.InstagramApiTest;
import com.kara4k.mediagrub.di.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface TestApiComponent {

    InstApi shareInstApi();

    void injectInstApiTest(InstagramApiTest instagramApiTest);

}
