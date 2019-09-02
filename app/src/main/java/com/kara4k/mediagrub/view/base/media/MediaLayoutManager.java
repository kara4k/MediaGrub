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

    MediaLayoutManager(final Context context, final RecyclerView recyclerView, final MediaAdapter adapter, final RecyclerView.LayoutManager layoutManager) {
        mContext = context;
        mRecyclerView = recyclerView;
        mAdapter = adapter;
        mLayoutManager = layoutManager;
    }

    public void onConfigurationChanged(final ScreenConfig screenConfig) {
        final int position = getCurrentPosition();

        if (mLayoutManager instanceof GridLayoutManager) {
            mLayoutManager = createGridLayoutManager(screenConfig.getOrientation());
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        mAdapter.setScreenConfig(screenConfig);
        mRecyclerView.scrollToPosition(position);
    }

    public void setLayout(final int layoutType, final int orientation) {
        final int position = getCurrentPosition();
        final RecyclerView.LayoutManager layoutManager;

        if (layoutType == MediaAdapter.LINEAR) {
            layoutManager = new LinearLayoutManager(mContext);
        } else {
            layoutManager = createGridLayoutManager(orientation);
        }

        mAdapter.setHolderViewType(layoutType);
        mRecyclerView.setLayoutManager(mLayoutManager = layoutManager);
        mRecyclerView.scrollToPosition(position);
    }

    @NonNull
    private GridLayoutManager createGridLayoutManager(final int orientation) {
        final int spanCount = (orientation == PORTRAIT) ? 2 : 3;
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

    public void setCurrentPosition(final int position) {
        mRecyclerView.scrollToPosition(position);
    }
}


