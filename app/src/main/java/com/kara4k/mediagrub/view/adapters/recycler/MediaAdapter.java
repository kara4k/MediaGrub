package com.kara4k.mediagrub.view.adapters.recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.view.adapters.ScreenConfig;
import com.kara4k.mediagrub.view.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class MediaAdapter extends BaseAdapter<MediaItem, MediaAdapter.MediaHolder> {

    public static final int LINEAR = 0;
    public static final int GRID = 1;

    private MediaListPresenter mPresenter;
    private int mHolderViewType;
    private ScreenConfig mScreenConfig;
    private boolean mIsActionMode;

    public MediaAdapter(MediaListPresenter presenter, ScreenConfig screenConfig) {
        mPresenter = presenter;
        mScreenConfig = screenConfig;
    }

    @Override
    public MediaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int holderLayout = getHolderLayout(viewType);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        return new MediaHolder(view);
    }

    private int getHolderLayout(int viewType) {
        int layoutRes;

        switch (viewType) {
            case LINEAR:
                layoutRes = R.layout.holder_media_linear;
                break;
            case GRID:
                layoutRes = R.layout.holder_media_grid;
                break;
            default:
                layoutRes = R.layout.holder_media_linear;
        }
        return layoutRes;
    }

    @Override
    public int getItemViewType(int position) {
        return mHolderViewType;
    }

    public void setHolderViewType(int holderViewType) {
        mHolderViewType = holderViewType;
    }

    public void setScreenConfig(ScreenConfig screenConfig) {
        mScreenConfig = screenConfig;
        notifyDataSetChanged();
    }

    @Override
    public void setItemSelected(int position, boolean isSelected) {
        getList().get(position).setSelected(isSelected);
    }

    public void setActionMode(boolean actionMode) {
        mIsActionMode = actionMode;
    }

    @Override
    public void finishActionMode() {
        setActionMode(false);
        super.finishActionMode();
    }

    public class MediaHolder extends BaseHolder<MediaItem> {

        @BindView(R.id.thumb_image_view)
        ImageView mPreviewImageView;
        @BindView(R.id.title_text_view)
        TextView mTitleTextView;
        @BindView(R.id.desc_text_view)
        TextView mDescTextView;
        @BindView(R.id.addition_text_view)
        TextView mAdditionTextView;

        public MediaHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(MediaItem mediaItem, int position) {
            super.onBind(mediaItem, position);
            setPreviewSize();
            Picasso.with(mContext).load(mediaItem.getThumbUrl()).into(mPreviewImageView);
            mTitleTextView.setText(mediaItem.getTitle());
            mDescTextView.setText(mediaItem.getDescription());
            mAdditionTextView.setText(mediaItem.getAddition());
        }

        private void setPreviewSize() {
            if (mHolderViewType == GRID) {
                int size = calcImageViewSide();

                mPreviewImageView.getLayoutParams().height = size;
                mPreviewImageView.getLayoutParams().width = size;
            }
        }

        private int calcImageViewSide() {
            int orientation = mScreenConfig.getOrientation();
            int screenWidth = mScreenConfig.getScreenSize().x;

            return (orientation == BaseFragment.PORTRAIT) ? screenWidth / 2 : screenWidth / 3;
        }

        @Override
        public void onClick(View view) {
            toggleSelection();
            mPresenter.onItemClicked(mItem, getAdapterPosition());
        }

        private void toggleSelection() {
            if (mIsActionMode) {
                itemView.setSelected(!itemView.isSelected());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            mPresenter.startActionMode(getAdapterPosition());
            toggleSelection();
            return true;
        }
    }
}
