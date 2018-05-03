package com.kara4k.mediagrub.view.vk.custom;


import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.vk.VkCustomGroupCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class VkCustomGroupCreatorFragment extends CustomCreatorFragment<VkCustomGroupCreatorPresenter> {

    public static VkCustomGroupCreatorFragment newInstance() {
        return new VkCustomGroupCreatorFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkCustomGroupCreatorFrag(this);
    }
}
