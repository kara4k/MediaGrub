package com.kara4k.mediagrub.presenter.base;


import android.util.SparseBooleanArray;

import com.kara4k.mediagrub.view.base.ListViewIF;

import java.util.ArrayList;
import java.util.List;

public class ListSelector<T> {

    private ListViewIF<T> mViewIF;
    private List<T> mItems;

    private boolean mIsActionMode;
    private SparseBooleanArray mSelectedItems;
    private int mSelectedCount;

    ListSelector() {
        resetSelections();
    }

    public void setItems(List<T> items) {
        mItems = items;
    }

    void startSelection(ListViewIF<T> viewIF, int position) {
        mViewIF = viewIF;
        mIsActionMode = true;
        mViewIF.startActionMode();
        toggleSelection(position);
    }

    void toggleSelection(int position) {
        boolean isSelected = !mSelectedItems.get(position, false);
        mSelectedCount = isSelected ? ++mSelectedCount : --mSelectedCount;

        if (mSelectedCount == 0) {
            finishActionMode();
            return;
        }

        mSelectedItems.put(position, isSelected);
        mViewIF.setItemSelected(position, isSelected);
        mViewIF.showSelectedCount(String.valueOf(mSelectedCount));
    }

    public void finishActionMode() {
        mViewIF.finishActionMode();
    }

    void resetSelections() {
        mIsActionMode = false;
        mSelectedItems = new SparseBooleanArray();
        mSelectedCount = 0;
        mViewIF = null;
    }

    public List<T> getSelectedItems() {
        List<T> items = new ArrayList<>();
        for (int i = 0; i < mSelectedItems.size(); i++) {
            boolean isSelected = mSelectedItems.valueAt(i);
            int key = mSelectedItems.keyAt(i);

            if (isSelected) {
                items.add(mItems.get(key));
            }
        }
        return items;
    }

    boolean isActionMode(){
        return mIsActionMode;
    }

    public void selectAll() {
        mSelectedCount = mItems.size();

        for (int i = 0; i < mItems.size(); i++) {
            mSelectedItems.put(i, true);
        }

        mViewIF.setSelectedAll();
        mViewIF.showSelectedCount(String.valueOf(mSelectedCount));
    }
}
