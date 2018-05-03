package com.kara4k.mediagrub.view.vk.auth;


import android.os.Bundle;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.vk.VkAuthPresenter;
import com.kara4k.mediagrub.view.base.AuthFragment;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

public class VkAuthFragment extends AuthFragment<VkAuthPresenter> {

    public static VkAuthFragment newInstance() {
        Bundle args = new Bundle();
        VkAuthFragment fragment = new VkAuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkAuthFragment(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onStart();
    }

    @Override
    public void showAuthDialog() {
        VKSdk.login(getActivity(), VKScope.FRIENDS, VKScope.PHOTOS);
    }

    @Override
    protected int getServiceLogoRes() {
        return R.drawable.vk_logo;
    }
}
