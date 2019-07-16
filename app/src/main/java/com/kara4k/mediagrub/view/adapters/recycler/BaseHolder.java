package com.kara4k.mediagrub.view.adapters.recycler;


import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseHolder<T extends SelectableItem> extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    protected Context mContext;
    protected T mItem;

    public BaseHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @CallSuper
    public void onBind(T t, int position) {
        mItem = t;
        itemView.setSelected(t.isSelected());
    }

}
