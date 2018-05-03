package com.kara4k.mediagrub.view.adapters.recycler;


public abstract class SelectableItem {

    private boolean mIsSelected;

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }
}
