package com.kara4k.mediagrub.view.inst.custom;


import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.inst.InstCustomUsersCreatorPresenter;
import com.kara4k.mediagrub.view.base.CustomCreatorFragment;

public class InstCustomUsersCreatorFragment
        extends CustomCreatorFragment<InstCustomUsersCreatorPresenter> {

    public static InstCustomUsersCreatorFragment newInstance() {
        return new InstCustomUsersCreatorFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectInstCustomUsersCreator(this);
    }
}
