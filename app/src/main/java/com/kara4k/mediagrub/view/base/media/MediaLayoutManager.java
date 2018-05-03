package com.kara4k.mediagrub.view.base.media;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kara4k.mediagrub.view.adapters.ScreenConfig;
import com.kara4k.mediagrub.view.adapters.recycler.MediaAdapter;

import static com.kara4k.mediagrub.view.base.BaseFragment.PORTRAIT;

class MediaLayoutManager {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private MediaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    MediaLayoutManager(Context context, RecyclerView recyclerView, MediaAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        mContext = context;
        mRecyclerView = recyclerView;
        mAdapter = adapter;
        mLayoutManager = layoutManager;
    }

    public void onConfigurationChanged(ScreenConfig screenConfig) {
        int position = getCurrentPosition();

        if (mLayoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = getGridLayoutManager(screenConfig.getOrientation());
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }

        mAdapter.setScreenConfig(screenConfig);
        mRecyclerView.scrollToPosition(position);
    }

    public void setLayout(int layoutType, int orientation) {
        int position = getCurrentPosition();
        RecyclerView.LayoutManager layoutManager;

        if (layoutType == MediaAdapter.LINEAR) {
            layoutManager = new LinearLayoutManager(mContext);
        } else {
            layoutManager = getGridLayoutManager(orientation);
        }

        mAdapter.setHolderViewType(layoutType);
        mRecyclerView.setLayoutManager(mLayoutManager = layoutManager);
        mRecyclerView.scrollToPosition(position);
    }

    @NonNull
    private GridLayoutManager getGridLayoutManager(int orientation) {
        int spanCount = (orientation == PORTRAIT) ? 2 : 3;
        return new GridLayoutManager(mContext, spanCount);
    }

    private int getCurrentPosition() {
        int position = 0;

        if (mLayoutManager != null && mLayoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }
        return position;
    }

    public void onDestroy() {
        mContext = null;
        mLayoutManager = null;
        mRecyclerView = null;
        mAdapter = null;
    }

    public void setCurrentPosition(int position) {
        mRecyclerView.scrollToPosition(position);
    }
}


