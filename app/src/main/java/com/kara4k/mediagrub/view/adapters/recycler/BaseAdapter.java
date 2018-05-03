package com.kara4k.mediagrub.view.adapters.recycler;


import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T extends SelectableItem, H extends BaseHolder<T>> extends RecyclerView.Adapter<H> {

    private List<T> mList;

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.onBind(mList.get(position), ++position);
    }

    public void finishActionMode() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setList(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemSelected(int position, boolean isSelected) {
        mList.get(position).setSelected(isSelected);
        notifyItemChanged(position);
    }

    public void setSelectedAll() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setSelected(true);
        }
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }
}
