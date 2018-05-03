package com.kara4k.mediagrub.view.vk.custom;


import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.vk.VkCustomUserCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class VkCustomUsersCreatorFragment extends CustomCreatorFragment<VkCustomUserCreatorPresenter> {

    public static VkCustomUsersCreatorFragment newInstance() {
        return new VkCustomUsersCreatorFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkCustomUsersCreatorFrag(this);
    }
}
