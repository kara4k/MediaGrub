package com.kara4k.mediagrub.view.base;


import java.util.List;

public interface ListViewIF<T> extends ViewIF {

    void setItems(List<T> list);

    void startActionMode();

    void finishActionMode();

    void setItemSelected(int position, boolean isSelected);

    void showSelectedCount(String count);

    void setSelectedAll();

    void checkWriteSdPermissions(int requestCode);

    void onDeleteSelected();

    void showLoading();

    void hideLoading();

    void onLoadingCancel();

    void showHint();

    void hideHint();

    void setToolbarTitle(String title, String subtitle);
}
