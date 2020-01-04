package com.kara4k.mediagrub.view.twitter;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.twitter.TwitterUserSearchPresenter;
import com.kara4k.mediagrub.view.base.UserSearchFragment;

public class TwitterUserSearchFragment extends UserSearchFragment<TwitterUserSearchPresenter> {

    public static TwitterUserSearchFragment newInstance() {
        return new TwitterUserSearchFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectTwitterUserSearchFragment(this);
    }
}
