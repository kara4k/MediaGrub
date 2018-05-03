package com.kara4k.mediagrub.view.base;


import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.custom.LockableViewPager;

import butterknife.BindView;

public abstract class ViewPagerFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    LockableViewPager mViewPager;

    protected abstract PagerAdapter getAdapter();

    @Override
    protected int getLayout() {
        return R.layout.view_pager;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.empty_menu;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        setToolbarTitle();
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(getAdapter());
    }

    protected void setToolbarTitle() {
        setTitle(getString(R.string.app_name), null);
    }

    public void setSwipeLocked(boolean isLocked) {
        int visibility = isLocked ? View.GONE : View.VISIBLE;
        mTabLayout.setVisibility(visibility);
        mViewPager.setSwipeLocked(isLocked);
    }

    private void setViewPagerPosition(int position) {
        mViewPager.setCurrentItem(position);
    }

}
