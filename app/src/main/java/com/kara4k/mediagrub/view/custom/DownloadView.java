package com.kara4k.mediagrub.view.custom;


import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kara4k.mediagrub.R;

import java.util.Locale;

public class DownloadView extends RelativeLayout {

    private TextView mTitleTextView;
    private TextView mServiceTextView;
    private TextView mSummaryTextView;
    private TextView mProgressTextView;
    private ProgressBar mProgressBar;
    private LinearLayout mActionsLayout;
    private ImageButton mPlayPauseImgBtn;
    private ImageButton mStopImgBtn;
    private ImageButton mDeleteImgBtn;

    private long mTaskId;
    private boolean mIsRunning;

    public DownloadView(Context context) {
        super(context);
        inflateViews(context);
    }

    private void inflateViews(Context context) {
        inflate(context, R.layout.download_view, this);
        mTitleTextView = findViewById(R.id.title_text_view);
        mServiceTextView = findViewById(R.id.service_text_view);
        mSummaryTextView = findViewById(R.id.summary_text_view);
        mProgressTextView = findViewById(R.id.progress_text_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mActionsLayout = findViewById(R.id.actions_layout);
        mPlayPauseImgBtn = findViewById(R.id.play_pause_img_btn);
        mStopImgBtn = findViewById(R.id.stop_img_btn);
        mDeleteImgBtn = findViewById(R.id.delete_img_btn);
    }

    public void setTaskId(long id){
        mTaskId = id;
    }

    public long getTaskId() {
        return mTaskId;
    }

    public void setTitle(String title) {
        mTitleTextView.setText(title);
    }

    public void setService(String service){
        mServiceTextView.setText(service);
    }

    public void setSummary(String summary) {
        mSummaryTextView.setText(summary);
    }

    public void setProgress(int progress, int max) {
        mProgressBar.setMax(max);
        mProgressBar.setProgress(progress);
        mProgressTextView.setText(String.format(Locale.ENGLISH, "%d/%d", progress, max));
    }

    public void setProgressMax(int max) {
        mProgressBar.setMax(max);
    }

    public void setProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    public void setOnPlayPauseListener(OnClickListener listener) {
        mPlayPauseImgBtn.setOnClickListener(listener);
    }

    public void setOnStopListener(OnClickListener listener) {
        mStopImgBtn.setOnClickListener(listener);
    }

    public void setOnDeleteListener(OnClickListener listener) {
        mDeleteImgBtn.setOnClickListener(listener);
    }

    public void setRunning(){
        mIsRunning = true;
        mPlayPauseImgBtn.setImageResource(R.drawable.ic_pause_black_24dp);
    }

    public void setPaused(){
        mIsRunning = false;
        mPlayPauseImgBtn.setImageResource(R.drawable.ic_play_arrow_black_24dp);
    }

    public void togglePlayPause(){
        mIsRunning = !mIsRunning;
        int resImg = mIsRunning ? R.drawable.ic_pause_black_24dp : R.drawable.ic_play_arrow_black_24dp;
        mPlayPauseImgBtn.setImageResource(resImg);
    }

    public boolean isRunning() {
        return mIsRunning;
    }
}
