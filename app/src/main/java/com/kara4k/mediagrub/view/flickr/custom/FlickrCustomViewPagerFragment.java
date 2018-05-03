package com.kara4k.mediagrub.view.flickr.custom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.ViewPagerFragment;

public class FlickrCustomViewPagerFragment extends ViewPagerFragment {

    public static FlickrCustomViewPagerFragment newInstance() {
        Bundle args = new Bundle();
        FlickrCustomViewPagerFragment fragment = new FlickrCustomViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected PagerAdapter getAdapter() {
        return new Adapter(getChildFragmentManager());
    }

    @Override
    protected void setToolbarTitle() {
        setTitle(getString(R.string.app_name), getString(R.string.nav_item_flickr));
    }

    class Adapter extends FragmentStatePagerAdapter {

        CharSequence[] mTitles;

        public Adapter(FragmentManager fm) {
            super(fm);
            setupTitles();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FlickrCustomUsersListFragment.newInstance();
                case 1:
                    return FlickrCustomGroupsListFragment.newInstance();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        private void setupTitles() {
            mTitles = new CharSequence[2];
            mTitles[0] = getString(R.string.vp_custom_users_title);
            mTitles[1] = getString(R.string.vp_custom_groups_title);
        }
    }
}
