package com.kara4k.mediagrub.view.vk;


import android.os.Bundle;

import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.base.AlbumsPresenter;
import com.kara4k.mediagrub.presenter.vk.VkPhotoAlbumsPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.AlbumsFragment;

import javax.inject.Inject;

public class VkPhotoAlbumsFragment extends AlbumsFragment{

    @Inject
    VkPhotoAlbumsPresenter mPresenter;

    @Override
    protected AlbumsPresenter getAlbumsPresenter() {
        return mPresenter;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkPhotoAlbumsFrag(this);
    }

    @Override
    public void showAlbumContent(UserItem userItem, AlbumItem albumItem) {
        addFragment(VkPhotoListFragment.newInstance(userItem, albumItem));
    }

    public static VkPhotoAlbumsFragment newInstance(UserItem userItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        VkPhotoAlbumsFragment fragment = new VkPhotoAlbumsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
