package com.kara4k.mediagrub.view.twitter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.ViewPagerFragment;

public class TwitterMediaViewPagerFragment extends ViewPagerFragment {

    public static final String USER = "user";

    private UserItem mUserItem;

    public static TwitterMediaViewPagerFragment newInstance(UserItem userItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        TwitterMediaViewPagerFragment fragment = new TwitterMediaViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewReady() {
        mUserItem = (UserItem) getArguments().getSerializable(USER);
        super.onViewReady();
    }

    @Override
    protected PagerAdapter getAdapter() {
        return new Adapter(getChildFragmentManager());
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
                    return TwitterPhotoListFragment.newInstance(mUserItem, null);
                case 1:
                    return TwitterVideoListFragment.newInstance(mUserItem, null);
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

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        private void setupTitles() {
            mTitles = new CharSequence[2];
            mTitles[0] = getString(R.string.vp_title_photo);
            mTitles[1] = getString(R.string.vp_title_video);
        }
    }
}
