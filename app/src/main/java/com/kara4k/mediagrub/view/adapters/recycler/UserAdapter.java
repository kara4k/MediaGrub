package com.kara4k.mediagrub.view.adapters.recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.view.base.UsersViewIF;

public class UserAdapter extends BaseAdapter<UserItem, UserAdapter.UserHolder> {

    private final ListPresenter<UserItem, UsersViewIF> mPresenter;

    public UserAdapter(final ListPresenter<UserItem, UsersViewIF> presenter) {
        mPresenter = presenter;
    }

    @Override
    public UserHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_user, parent, false);
        return new UserHolder(view);
    }

    public class UserHolder extends AbstractUserHolder {

        public UserHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(final View view) {
            mPresenter.onItemClicked(mItem, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(final View view) {
            mPresenter.startActionMode(getAdapterPosition());
            return true;
        }
    }
}
