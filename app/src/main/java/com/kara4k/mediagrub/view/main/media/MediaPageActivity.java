package com.kara4k.mediagrub.view.main.media;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.other.Serializer;
import com.kara4k.mediagrub.view.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MediaPageActivity extends BaseActivity {

    public static final String POSITION = "position";
    public static final int EMPTY = -1;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private Serializer mSerializer = new Serializer(this);

    @Override
    protected int getContentView() {
        return R.layout.activity_media_pager;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setFullScreen();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewReady() {
        setupToolbar();

        int position = getIntent().getIntExtra(POSITION, EMPTY);

        Single.fromCallable(mSerializer::readItems)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mediaItems -> onComplete(mediaItems, position), this::onError);
    }

    private void setupToolbar(){
        try {
            getSupportActionBar().setShowHideAnimationEnabled(true);
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    private void onComplete(List<MediaItem> mediaItems, int position) {
        mViewPager.setAdapter(new Adapter(getSupportFragmentManager(), mediaItems));
        if (position != EMPTY) mViewPager.setCurrentItem(position);
    }

    private void onError(Throwable throwable) throws Exception {
        throwable.printStackTrace();
        showToast(throwable.getMessage());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(POSITION, mViewPager.getCurrentItem());

        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    public static Intent newIntent(Context context, int position) {
        Intent intent = new Intent(context, MediaPageActivity.class);
        intent.putExtra(POSITION, position);
        return intent;
    }

    class Adapter extends FragmentStatePagerAdapter {

        private List<MediaItem> mItems;

        public Adapter(FragmentManager fm, List<MediaItem> mediaItems) {
            super(fm);
            mItems = mediaItems;
        }

        @Override
        public Fragment getItem(int position) {
            return MediaPageFragment.newInstance(mItems.get(position));
        }

        @Override
        public int getCount() {
            return mItems.size();
        }
    }
}
