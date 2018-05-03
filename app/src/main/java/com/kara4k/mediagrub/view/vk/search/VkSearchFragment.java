package com.kara4k.mediagrub.view.vk.search;


import android.os.Bundle;
import android.view.Menu;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.presenter.vk.VkPhotoSearchPresenter;
import com.kara4k.mediagrub.view.base.media.SearchFragment;

import javax.inject.Inject;

public class VkSearchFragment extends SearchFragment {

    @Inject
    VkPhotoSearchPresenter mPresenter;

    public static VkSearchFragment newInstance() {
        Bundle args = new Bundle();
        VkSearchFragment fragment = new VkSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onMenuInflated(Menu menu) {
        super.onMenuInflated(menu);
        menu.findItem(R.id.menu_item_load_all).setVisible(false);
    }

    @Override
    protected SearchPresenter getSearchPresenter() {
        return mPresenter;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkSearchFrag(this);
    }


}
