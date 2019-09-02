package com.kara4k.mediagrub.view.main.media;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.main.MediaPagePresenter;
import com.kara4k.mediagrub.view.base.BaseFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

public class MediaPageFragment extends BaseFragment implements MediaPageViewIF {

    private static final String MEDIA_ITEM = "media_item";

    @BindView(R.id.photo_image_view)
    ImageView mImageView;
    @BindView(R.id.video_view)
    VideoView mVideoView;
    @BindView(R.id.title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.desc_text_view)
    TextView mDescTextView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Inject
    MediaPagePresenter mPresenter;
    private MediaController mMediaController;

    @Override
    protected int getLayout() {
        return R.layout.fragment_media_page;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.fragment_media_page_menu;
    }

    @Override
    public void showError(final String message) {
        showToast(message);
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectMediaPageFragment(this);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onViewReady() {
        final MediaItem mediaItem = (MediaItem) getArguments().getSerializable(MEDIA_ITEM);
        mImageView.setOnClickListener(view -> mPresenter.onPhotoClicked());
        mPresenter.onStart(mediaItem);
        setUserVisibleHint(isMenuVisible());
    }

    private void setupVideoView() {
        mMediaController = new MediaController(getContext());
        mMediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mMediaController);

        mVideoView.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                mPresenter.onVideoClicked();
            }
            return true;
        });
    }

    @Override
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mPresenter != null) {
            mPresenter.onVisible(isVisibleToUser);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                mPresenter.onShareClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showSendTo(final String sourceUrl) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, sourceUrl);
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_send_to)));
    }

    @Override
    public void showPhoto(final MediaItem mediaItem) {
        mVideoView.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

        final String sourceUrl = mediaItem.getSourceUrl();
        Picasso.with(getContext()).load(sourceUrl)
                .resize(3400, 3400)
                .onlyScaleDown()
                .into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
        showDescription(mediaItem);
    }

    @Override
    public void showVideo(final MediaItem mediaItem) {
        mImageView.setVisibility(View.GONE);
        mVideoView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

        mVideoView.setOnPreparedListener(mediaPlayer -> mProgressBar.setVisibility(View.GONE));

        setupVideoView();
        showDescription(mediaItem);

        final Uri videoUri = Uri.parse(mediaItem.getSourceUrl());
        mVideoView.setVideoURI(videoUri);
    }

    @Override
    public void startVideo() {
        mVideoView.start();
        mVideoView.resume();
    }

    @Override
    public void stopVideo() {
        mVideoView.stopPlayback();
        mMediaController.hide();
    }

    private void showDescription(final MediaItem mediaItem) {
        final String title = mediaItem.getTitle();
        final String description = mediaItem.getDescription();

        if (title != null) mTitleTextView.setText(title);
        if (description != null) mDescTextView.setText(description);
    }

    @Override
    public void toggleMediaController() {
        if (mMediaController.isShowing()) {
            mMediaController.hide();
        } else {
            mMediaController.show();
        }
    }

    @Override
    public void toggleActions() {
        toggleActionBar();
    }

    public static MediaPageFragment newInstance(final MediaItem mediaItem) {
        final Bundle args = new Bundle();
        args.putSerializable(MEDIA_ITEM, mediaItem);
        final MediaPageFragment fragment = new MediaPageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
