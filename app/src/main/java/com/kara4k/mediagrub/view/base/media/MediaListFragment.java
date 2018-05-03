package com.kara4k.mediagrub.view.base.media;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.download.DownloadService;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.BaseAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.MediaAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.BaseListFragment;
import com.kara4k.mediagrub.view.main.media.MediaPageActivity;

public abstract class MediaListFragment extends BaseListFragment<MediaItem, MediaAdapter.MediaHolder>
        implements MediaListViewIF {

    private static final int MENU_ITEM_LAYOUT_ID = 101;
    private static final int REQUEST_POSITION = 1;

    protected static final String USER = "user";
    protected static final String ALBUM = "album";

    protected UserItem mUserItem;
    protected AlbumItem mAlbumItem;

    private MediaAdapter mAdapter;
    private MediaLayoutManager mMediaLayoutManager;
    private Menu mMenu;

    protected abstract MediaListPresenter getMediaListPresenter();

    @Override
    protected BaseAdapter<MediaItem, MediaAdapter.MediaHolder> getAdapter() {
        return mAdapter;
    }

    @Override
    protected ListPresenter getListPresenter() {
        return getMediaListPresenter();
    }

    @Override
    protected void onSetLayoutManager() {
        getMediaListPresenter().onSetLayout();
    }

    @Override
    protected void onFragmentCreate() {
        mAdapter = new MediaAdapter(getMediaListPresenter(), getScreenConfig());
        mUserItem = (UserItem) getArguments().getSerializable(USER);
        mAlbumItem = (AlbumItem) getArguments().getSerializable(ALBUM);
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        getMediaListPresenter().onStart(mUserItem, mAlbumItem);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mMediaLayoutManager.onConfigurationChanged(getScreenConfig());
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected int getMenuRes() {
        return R.menu.fragment_media_list_menu;
    }

    @Override
    protected void onMenuInflated(Menu menu) {
        super.onMenuInflated(menu);
        mMenu = menu;
        getMediaListPresenter().onMenuInflate();
    }

    @Override
    public void setLayoutMenuItem(int layoutType) {
        if (mMenu == null) return;

        MenuItem layoutItem;

        if (mMenu.findItem(MENU_ITEM_LAYOUT_ID) == null) {
            layoutItem = mMenu.add(Menu.NONE, MENU_ITEM_LAYOUT_ID,
                    2, R.string.menu_item_layout);

            layoutItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            layoutItem = mMenu.findItem(MENU_ITEM_LAYOUT_ID);
        }

        if (layoutType == MediaAdapter.LINEAR) {
            layoutItem.setIcon(R.drawable.ic_dashboard_white_24dp);
        } else {
            layoutItem.setIcon(R.drawable.ic_list_white_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_LAYOUT_ID:
                getMediaListPresenter().onToggleLayout();
                return true;
            case R.id.menu_item_load_all:
                getMediaListPresenter().loadAll();
                return true;
            default:
                onOptionsSelected(item);
        }
        return true;
    }

    @Override
    public void showTaskIsCreating() {
        String title = getString(R.string.dialog_task_prepare_title);
        String message = getString(R.string.dialog_task_prepare_message);

        showLoadingDialog(title, message, UNDEFINED, UNDEFINED, false);
    }

    @Override
    public void showAlbumLoadProgress(int progress, int max) {
        String title = getString(R.string.dialog_title_loading);
        showLoadingDialog(title, null, progress, max, true);
    }

    @Override
    public void updateAlbumProgress(int progress) {
        updateDialogProgress(progress);
    }

    @Override
    protected void onProgressDialogCancel() {
        getMediaListPresenter().onCancelLoadAll();
    }

    @Override
    public void setLayout(int layoutType) {
        if (mMediaLayoutManager == null) {
            mMediaLayoutManager = new MediaLayoutManager(getContext(), mRecyclerView,
                    (MediaAdapter) mAdapter, mLayoutManager);
        }
        mMediaLayoutManager.setLayout(layoutType, getOrientation());
    }

    @Override
    public void startActionMode() {
        mAdapter.setActionMode(true);
        setModeCallback(new MediaListCallback());
    }

    @Override
    public void showDetails(int position) {
        Intent intent = MediaPageActivity.newIntent(getContext(), position);
        activityStartForResult(intent, REQUEST_POSITION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_POSITION && resultCode == Activity.RESULT_OK) {
            int position = data.getIntExtra(MediaPageActivity.POSITION, MediaPageActivity.EMPTY);
            getMediaListPresenter().onLastDetailPosition(position);
        }
    }

    @Override
    public void setCurrentPosition(int position) {
        mMediaLayoutManager.setCurrentPosition(position);
    }

    @Override
    public void startDownload(long taskId) {
        finishActionMode();
        Intent intent = new Intent(DownloadService.newIntent(getContext()));
        DownloadService.setConnected(true);
        getActivity().startService(intent);
        getActivity().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                DownloadService service = ((DownloadService.Binder) iBinder).getService();
                service.startTask(taskId);
                DownloadService.setConnected(false);
                getActivity().unbindService(this);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        }, 0);
    }

    protected void onOptionsSelected(MenuItem item) {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaLayoutManager.onDestroy();
    }

    class MediaListCallback extends ModeCallback {

        @Override
        protected int getModeMenu() {
            return R.menu.fragment_action_media_list;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_item_action_select_all:
                    getListPresenter().selectAll();
                    break;
                case R.id.menu_item_action_download:
                    getMediaListPresenter().onDownloadSelected();
                    break;
            }
            return false;
        }
    }
}
