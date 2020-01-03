package com.kara4k.mediagrub.view.inst.search;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.inst.InstUserSearchPresenter;
import com.kara4k.mediagrub.view.base.UserSearchFragment;

public class InstUserSearchFragment extends UserSearchFragment<InstUserSearchPresenter> {

    public static InstUserSearchFragment newInstance() {
        return new InstUserSearchFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectInstUserSearchFragment(this);
    }

}
