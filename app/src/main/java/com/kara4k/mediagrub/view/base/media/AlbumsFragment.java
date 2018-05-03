package com.kara4k.mediagrub.view.base.media;


import android.support.v7.view.ActionMode;
import android.view.MenuItem;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.AlbumsPresenter;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.BaseAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.BaseListFragment;

public abstract class AlbumsFragment extends BaseListFragment<AlbumItem, AlbumAdapter.AlbumHolder>
        implements AlbumViewIF {

    public static final String USER = "user";

    private UserItem mUserItem;

    protected abstract AlbumsPresenter getAlbumsPresenter();

    @Override
    protected BaseAdapter<AlbumItem, AlbumAdapter.AlbumHolder> getAdapter() {
        return new AlbumAdapter(getListPresenter());
    }

    @Override
    protected ListPresenter<AlbumItem, AlbumViewIF> getListPresenter() {
        return getAlbumsPresenter();
    }

    @Override
    protected void onFragmentCreate() {
        mUserItem = (UserItem) getArguments().getSerializable(USER);
    }

    @Override
    public void onStart() {
        super.onStart();
        getAlbumsPresenter().onStart(mUserItem);
    }

    @Override
    public void startActionMode() {
        ModeCallback callback = new ModeCallback() {
            @Override
            protected int getModeMenu() {
                return R.menu.empty_menu;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        };

        setModeCallback(callback);
    }
}
