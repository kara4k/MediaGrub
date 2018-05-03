package com.kara4k.mediagrub.view.adapters.recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.view.base.media.AlbumViewIF;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class AlbumAdapter extends BaseAdapter<AlbumItem, AlbumAdapter.AlbumHolder> {

    private ListPresenter<AlbumItem, AlbumViewIF> mPresenter;

    public AlbumAdapter(ListPresenter<AlbumItem, AlbumViewIF> presenter) {
        mPresenter = presenter;
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_album, parent, false);
        return new AlbumHolder(view);
    }

    public class AlbumHolder extends BaseHolder<AlbumItem> {

        @BindView(R.id.cover_image_view)
        ImageView mCoverImageView;
        @BindView(R.id.title_text_view)
        TextView mTitleTextView;
        @BindView(R.id.desc_text_view)
        TextView mDescTextView;
        @BindView(R.id.size_text_view)
        TextView mSizeTextView;
        @BindView(R.id.num_text_view)
        TextView mNumTextView;

        public AlbumHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(AlbumItem albumItem, int position) {
            super.onBind(albumItem, position);
            Picasso.with(mContext)
                    .load(albumItem.getCoverUrl())
                    .error(R.drawable.noalbum)
                    .into(mCoverImageView);
            mTitleTextView.setText(albumItem.getTitle());
            mDescTextView.setText(albumItem.getDescription());
            mSizeTextView.setText(albumItem.getSize());
            mNumTextView.setText(String.valueOf(position));
        }

        @Override
        public void onClick(View view) {
            mPresenter.onItemClicked(mItem, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
