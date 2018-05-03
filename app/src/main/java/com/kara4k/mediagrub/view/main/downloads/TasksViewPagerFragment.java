package com.kara4k.mediagrub.view.main.downloads;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.ViewPagerFragment;

public class TasksViewPagerFragment extends ViewPagerFragment {

    public static TasksViewPagerFragment newInstance() {
        Bundle args = new Bundle();
        TasksViewPagerFragment fragment = new TasksViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setToolbarTitle() {
        setTitle(getString(R.string.downloads_title), null);
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
                    return ActiveTasksFragment.newInstance();
                case 1:
                    return CompletedTasksFragment.newInstance();
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
            mTitles[0] = getString(R.string.vp_title_active);
            mTitles[1] = getString(R.string.vp_title_completed);
        }
    }
}
