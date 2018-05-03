package com.kara4k.mediagrub.view.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kara4k.mediagrub.R;

public class ItemView extends RelativeLayout implements View.OnClickListener {


    private TextView mTitleTextView;
    private TextView mSummaryTextView;
    private CheckBox mCheckBox;
    private ImageView mIconImageView;
    private ImageView mPhotoImageView;
    private ClickListener mClickListener;

    private long uuid;
    private String mTitleText;
    private String mSummaryText;
    private boolean mIsCheckBoxVisible;
    private boolean mIsCheckBoxEnabled;
    private boolean mIsChecked;
    private boolean mIsToggleCheckBox;
    private boolean mIsIconVisible;
    private boolean mIsImageVisible;
    private Drawable mIconDrawable;
    private Drawable mImageDrawable;

    public interface ClickListener {
        void onClick();
    }

    public ItemView(Context context) {
        super(context);
        inflateViews(context);
        mIsCheckBoxVisible = false;
        mIsCheckBoxEnabled = true;
        mIsChecked = false;
        mIsToggleCheckBox = false;
        mIsIconVisible = false;
        mIsImageVisible = false;

        fillViews();
        setOnClickListener(this);
    }

    public ItemView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateViews(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        mTitleText = a.getString(R.styleable.ItemView_title_text);
        mSummaryText = a.getString(R.styleable.ItemView_summary_text);
        mIsCheckBoxVisible = a.getBoolean(R.styleable.ItemView_check_box_is_visible, false);
        mIsCheckBoxEnabled = a.getBoolean(R.styleable.ItemView_check_box_is_enabled, true);
        mIsChecked = a.getBoolean(R.styleable.ItemView_is_checked, false);
        mIsToggleCheckBox = a.getBoolean(R.styleable.ItemView_is_checkable, false);
        mIsIconVisible = a.getBoolean(R.styleable.ItemView_icon_is_visible, false);
        mIsImageVisible = a.getBoolean(R.styleable.ItemView_photo_is_visible, false);
        mIconDrawable = a.getDrawable(R.styleable.ItemView_icon_src);
        mImageDrawable = a.getDrawable(R.styleable.ItemView_photo_src);

        a.recycle();
        fillViews();
        setOnClickListener(this);
    }

    private void fillViews() {
        if (mTitleText != null) {
            mTitleTextView.setText(mTitleText);
        }

        if (mSummaryText != null) {
            mSummaryTextView.setText(mSummaryText);
        }

        if (mIsCheckBoxVisible) {
            mCheckBox.setVisibility(VISIBLE);
        } else {
            mCheckBox.setVisibility(INVISIBLE);
        }

        mCheckBox.setEnabled(mIsCheckBoxEnabled);
        mCheckBox.setChecked(mIsChecked);

        if (mIsIconVisible) {
            mIconImageView.setVisibility(VISIBLE);
        } else {
            mIconImageView.setVisibility(INVISIBLE);
        }

        if (mIconDrawable != null) {
            mIconImageView.setImageDrawable(mIconDrawable);
        }

        if (mIsImageVisible) {
            mPhotoImageView.setVisibility(VISIBLE);
        } else {
            mPhotoImageView.setVisibility(GONE);
        }

        if (mImageDrawable != null) {
            mPhotoImageView.setImageDrawable(mImageDrawable);
        }
    }

    private void inflateViews(Context context) {
        inflate(context, R.layout.item_view, this);
        mTitleTextView = (TextView) findViewById(R.id.first_name_text_view);
        mSummaryTextView = (TextView) findViewById(R.id.last_name_text_view);
        mCheckBox = (CheckBox) findViewById(R.id.check_box);
        mIconImageView = (ImageView) findViewById(R.id.icon);
        mPhotoImageView = (ImageView) findViewById(R.id.photo_image_view);
    }

    @Override
    public void onClick(View v) {
        if ((mCheckBox.isEnabled()) && (mIsToggleCheckBox)) {
            if (mCheckBox.isChecked()) {
                mCheckBox.setChecked(false);
            } else {
                mCheckBox.setChecked(true);
            }
        }
        if (mClickListener != null) {
            mClickListener.onClick();
        }
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public TextView getSummaryTextView() {
        return mSummaryTextView;
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

    public void setIconVisibility(int visibility) {
        mIconImageView.setVisibility(visibility);
    }

    public void setIconImageResource(int id) {
        mIconImageView.setImageResource(id);
    }

    public void setOnIconClickListener(OnClickListener listener) {
        mIconImageView.setOnClickListener(listener);
    }

    public ImageView getPhotoImageView() {
        return mPhotoImageView;
    }

    public void setImageVisibility(int visibility) {
        mPhotoImageView.setVisibility(visibility);
    }

    public ImageView getIconImageView() {
        return mIconImageView;
    }

    public void setSummary(String text) {
        this.mSummaryTextView.setText(text);
    }

    public void setTitle(String title) {
        this.mTitleTextView.setText(title);
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public boolean iconIsVisible() {
        return mIconImageView.getVisibility() == View.VISIBLE;
    }

    public void setChecked(boolean checked) {
        mCheckBox.setChecked(checked);
    }

    public boolean isChecked(){
        return mCheckBox.isChecked();
    }

    public void setCheckBoxEnabled(boolean enabled) {
        mCheckBox.setEnabled(enabled);
    }
}


