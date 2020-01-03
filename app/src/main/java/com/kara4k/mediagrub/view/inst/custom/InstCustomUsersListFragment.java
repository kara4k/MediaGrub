package com.kara4k.mediagrub.view.inst.custom;


import android.content.Intent;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.presenter.inst.InstCustomUsersListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.CustomUsersListFragment;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintCustom;
import com.kara4k.mediagrub.view.inst.InstPhotoListFragment;
import com.kara4k.mediagrub.view.main.UserCreatorActivity;
import com.kara4k.mediagrub.view.main.UserSearchActivity;

public class InstCustomUsersListFragment
        extends CustomUsersListFragment<InstCustomUsersListPresenter> {


    public static InstCustomUsersListFragment newInstance() {
        return new InstCustomUsersListFragment();
    }

    @Override
    protected boolean isHasUserSearchMenuItem() {
        return true;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectInstCustomUsers(this);
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        setTitle(getString(R.string.app_name), getString(R.string.nav_item_instagram));
    }

    @Override
    protected Hint getHint() {
        return new HintCustom() {
            @Override
            public String getMessage() {
                return getString(getTemplateResId(), getString(R.string.hint_users));
            }
        };
    }

    @Override
    public void showUserCreator() {
        final Intent intent = UserCreatorActivity.newIntent(
                getContext(), CustomUser.INSTAGRAM, CustomUser.USER);
        activityStart(intent);
    }

    @Override
    public void showUserSearch() {
        final Intent intent = UserSearchActivity.newIntent(
                getContext(), CustomUser.INSTAGRAM, CustomUser.USER);
        activityStart(intent);
    }

    @Override
    public void showAlbums(final UserItem userItem) {
        addFragment(InstPhotoListFragment.newInstance(userItem, null));
    }
}
