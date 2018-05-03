package com.kara4k.mediagrub.view.adapters.recycler;


import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.view.base.UsersViewIF;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class UserAdapter extends BaseAdapter<UserItem, UserAdapter.UserHolder> {

    private ListPresenter<UserItem, UsersViewIF> mPresenter;

    public UserAdapter(ListPresenter<UserItem, UsersViewIF> presenter) {
        mPresenter = presenter;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_user, parent, false);
        return new UserHolder(view);
    }

    public class UserHolder extends BaseHolder<UserItem> {

        @BindView(R.id.main_text_view)
        TextView mMainTextView;
        @BindView(R.id.addition_text_view)
        TextView mAdditionTextView;
        @BindView(R.id.photo_image_view)
        ImageView mPhotoImageView;
        @BindView(R.id.num_text_view)
        TextView mNumTextView;


        public UserHolder(View itemView) {
            super(itemView);
        }

        @CallSuper
        public void onBind(UserItem userItem, int position) {
            super.onBind(userItem, position);
            mMainTextView.setText(userItem.getMainText());
            mAdditionTextView.setText(userItem.getAdditionText());
            Picasso.with(mContext)
                    .load(userItem.getPhotoUrl())
                    .transform(new CropCircleTransformation())
                    .into(mPhotoImageView);
            mNumTextView.setText(String.valueOf(position));
        }

        @Override
        public void onClick(View view) {
            mPresenter.onItemClicked(mItem, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            mPresenter.startActionMode(getAdapterPosition());
            return true;
        }
    }
}
