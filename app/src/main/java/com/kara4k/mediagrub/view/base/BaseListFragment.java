package com.kara4k.mediagrub.view.base;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.BaseAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.BaseHolder;
import com.kara4k.mediagrub.view.adapters.recycler.SelectableItem;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.main.MainActivity;

import java.util.List;

import butterknife.BindView;

public abstract class BaseListFragment<T extends SelectableItem, H extends BaseHolder<T>>
        extends BaseFragment implements ListViewIF<T>, SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.hint_layout)
    View mHintLayout;

    protected BaseAdapter<T, H> mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ActionMode mActionMode;
    protected SearchView mSearchView;

    protected abstract BaseAdapter<T, H> getAdapter();

    protected abstract ListPresenter getListPresenter();

    @Override
    protected int getLayout() {
        return R.layout.recycler_view;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.fragment_list_menu;
    }

    @Override
    protected void onMenuInflated(final Menu menu) {
        mSearchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();
        mSearchView.setOnQueryTextListener(this);
    }

    @CallSuper
    @Override
    protected void onViewReady() {
        if (getHint() != null) setupHint(getHint());

        mRecyclerView.setAdapter(mAdapter = getAdapter());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                if (!recyclerView.canScrollVertically(1)) {
                    getListPresenter().onScrollEnd();
                }
            }
        });
        onSetLayoutManager();
    }

    protected void onSetLayoutManager() {
        mRecyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(getContext()));
    }

    @Override
    public void setItems(final List<T> list) {
        mAdapter.setList(list);
    }

    @Override
    public void showError(final String message) {
        showToast(message);
    }

    protected Hint getHint() {
        return null;
    }

    private void setupHint(final Hint hint) {
        final ImageView mHintImageView = mHintLayout.findViewById(R.id.hint_image);
        final TextView mHintTextView = mHintLayout.findViewById(R.id.hint_message);

        mHintLayout.setOnClickListener(view -> onHintClicked());
        if (hint.getIconRes() != Hint.UNDEFINED) mHintImageView.setImageResource(hint.getIconRes());
        mHintTextView.setText(hint.getMessage());
    }

    @Override
    public void showHint() {
        if (getHint()!= null) mHintLayout.setVisibility(View.VISIBLE);
    }

    protected void onHintClicked() {}

    @Override
    public void setToolbarTitle(final String title, final String subtitle) {
        setTitle(title, subtitle);
    }

    @Override
    public void hideHint() {
        if (getHint()!= null) mHintLayout.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingCancel() {
        hideDialog();
    }

    @Override
    public boolean onQueryTextSubmit(final String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        getListPresenter().onSearch(newText);
        return true;
    }

    @Override
    public void startActionMode() {
    }

    protected void setModeCallback(final ActionMode.Callback callback) {
        mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(callback);
    }

    @Override
    public void setItemSelected(final int position, final boolean isSelected) {
        mAdapter.setItemSelected(position, isSelected);
    }

    @Override
    public void setSelectedAll() {
        mAdapter.setSelectedAll();
    }

    @Override
    public void showSelectedCount(final String count) {
        if (mActionMode != null) {
            mActionMode.setTitle(count);
        }
    }

    @Override
    public void finishActionMode() {
        if (mActionMode != null) {
            mActionMode.finish();
            mActionMode = null;
        }
    }

    @Override
    public void onDeleteSelected() {
        final String title = getString(R.string.dialog_delete_title);
        final String message = getString(R.string.dialog_delete_selected);
        final DialogInterface.OnClickListener okListener = (dialogInterface, i)
                -> getListPresenter().onDeleteSelectedConfirm();

        showConfirmDialog(title, message, okListener);
    }

    @Override
    public void checkWriteSdPermissions(final int requestCode) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            final int hasWriteSDCardPermission = ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (hasWriteSDCardPermission == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        requestCode);
                return;
            }
        }
        getListPresenter().onStoragePermissionGranted(requestCode);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            getListPresenter().onStoragePermissionDenied(requestCode);
        } else {
            getListPresenter().onStoragePermissionGranted(requestCode);
        }
    }

    protected void setDrawerMode(final int lockMode) {
        ((MainActivity) getActivity()).setDrawerMode(lockMode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        final ListPresenter listPresenter = getListPresenter();
        if (listPresenter != null){
            listPresenter.onDestroy();
        }
    }

    protected abstract class ModeCallback implements ActionMode.Callback {

        protected abstract int getModeMenu();

        @Override
        public boolean onCreateActionMode(final ActionMode mode, final Menu menu) {
            mode.getMenuInflater().inflate(getModeMenu(), menu);
            setDrawerMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            setSwipeLock(true);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(final ActionMode mode, final Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(final ActionMode mode) {
            getListPresenter().onActionModeDestroy();
            mAdapter.finishActionMode();
            setDrawerMode(DrawerLayout.LOCK_MODE_UNDEFINED);
            setSwipeLock(false);
        }

        private void setSwipeLock(final boolean isLocked) {
            if (getParentFragment() instanceof ViewPagerFragment) {
                ((ViewPagerFragment) getParentFragment()).setSwipeLocked(isLocked);
            }
        }

    }
}
