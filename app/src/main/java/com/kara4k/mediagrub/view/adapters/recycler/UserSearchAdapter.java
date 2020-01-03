package com.kara4k.mediagrub.view.adapters.recycler;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.UserSearchPresenter;

import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.UserSearchHolder> {

    private final UserSearchPresenter mPresenter;
    private List<UserItem> mUserItems;

    public UserSearchAdapter(final UserSearchPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public UserSearchAdapter.UserSearchHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_user, parent, false);
        return new UserSearchAdapter.UserSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserSearchHolder holder, int position) {
        holder.onBind(mUserItems.get(position), ++position);
    }

    @Override
    public int getItemCount() {
        return mUserItems != null ? mUserItems.size() : 0;
    }

    public void setUserItems(final List<UserItem> items) {
        this.mUserItems = items;
        notifyDataSetChanged();
    }


    public class UserSearchHolder extends AbstractUserHolder {

        public UserSearchHolder(final View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(final UserItem userItem, final int position) {
            super.onBind(userItem, position);
            mMainTextView.setTextColor(Color.BLACK);
            mNumTextView.setTextColor(Color.BLACK);
            mPrivateUserIcon.setColorFilter(Color.GRAY);
        }

        @Override
        public void onClick(final View view) {
        }

        @Override
        public boolean onLongClick(final View view) {
            mPresenter.addUser(mItem);
            return true;
        }
    }
}
