package com.kara4k.mediagrub.view.adapters.recycler;


import android.support.annotation.NonNull;
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

    private final MediaListPresenter mPresenter;
    private int mHolderViewType;
    private ScreenConfig mScreenConfig;
    private boolean mIsActionMode;

    public MediaAdapter(final MediaListPresenter presenter, final ScreenConfig screenConfig) {
        mPresenter = presenter;
        mScreenConfig = screenConfig;
    }

    @NonNull
    @Override
    public MediaHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final int holderLayout = getHolderLayout(viewType);
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        return new MediaHolder(view);
    }

    private int getHolderLayout(final int viewType) {
        switch (viewType) {
            case GRID:
                return R.layout.holder_media_grid;
            case LINEAR:
            default:
                return R.layout.holder_media_linear;
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return mHolderViewType;
    }

    public void setHolderViewType(final int holderViewType) {
        mHolderViewType = holderViewType;
    }

    public void setScreenConfig(final ScreenConfig screenConfig) {
        mScreenConfig = screenConfig;
        notifyDataSetChanged();
    }

    @Override
    public void setItemSelected(final int position, final boolean isSelected) {
        getList().get(position).setSelected(isSelected);
    }

    public void setActionMode(final boolean actionMode) {
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

        public MediaHolder(final View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(final MediaItem mediaItem, final int position) {
            super.onBind(mediaItem, position);
            setPreviewSize();
            Picasso.with(mContext).load(mediaItem.getThumbUrl()).into(mPreviewImageView);
            mTitleTextView.setText(mediaItem.getTitle());
            mDescTextView.setText(mediaItem.getDescription());
            mAdditionTextView.setText(mediaItem.getAddition());
        }

        private void setPreviewSize() {
            if (mHolderViewType == GRID) {
                final int size = calcImageViewSide();

                mPreviewImageView.getLayoutParams().height = size;
                mPreviewImageView.getLayoutParams().width = size;
            }
        }

        private int calcImageViewSide() {
            final int orientation = mScreenConfig.getOrientation();
            final int screenWidth = mScreenConfig.getScreenSize().x;

            return (orientation == BaseFragment.PORTRAIT) ? screenWidth / 2 : screenWidth / 3;
        }

        @Override
        public void onClick(final View view) {
            toggleSelection();
            mPresenter.onItemClicked(mItem, getAdapterPosition());
        }

        private void toggleSelection() {
            if (mIsActionMode) {
                itemView.setSelected(!itemView.isSelected());
            }
        }

        @Override
        public boolean onLongClick(final View view) {
            mPresenter.startActionMode(getAdapterPosition());
            toggleSelection();
            return true;
        }
    }
}
