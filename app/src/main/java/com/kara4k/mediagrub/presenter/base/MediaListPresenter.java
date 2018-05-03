package com.kara4k.mediagrub.presenter.base;


import android.os.Environment;
import android.util.Log;

import com.kara4k.mediagrub.download.DownloadManager;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.database.Task;
import com.kara4k.mediagrub.other.Serializer;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.MediaAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class MediaListPresenter<V extends MediaListViewIF> extends ListPresenter<MediaItem, V> {

    @Inject
    DaoSession mDaoSession;
    @Inject
    Serializer mSerializer;

    protected UserItem mUserItem;
    protected AlbumItem mAlbumItem;

    private boolean mIsLoadAll;

    abstract protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded);

    public void onStart(UserItem userItem, AlbumItem albumItem) {
        mUserItem = userItem;
        mAlbumItem = albumItem;
        mHasMore = true;
        setupTitle();
        getView().showLoading();
    }

    protected void createAlbum(UserItem userItem) {
        mAlbumItem = new AlbumItem();
        mAlbumItem.setId(userItem.getAdditionText());
        mAlbumItem.setOwnerId(userItem.getId());
        mAlbumItem.setTitle(userItem.getAdditionText());
    }

    protected void setupTitle() {
        if (mUserItem != null && mAlbumItem != null) {
            getView().setToolbarTitle(mUserItem.getMainText(), mAlbumItem.getTitle());
        }
    }

    @Override
    protected boolean searchFilter(MediaItem mediaItem, String text) {
        if (mediaItem.getTitle() == null) return false;
        return mediaItem.getTitle().toLowerCase().contains(text.toLowerCase());
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        getView().hideLoading();
        super.onSuccess(list);
        setupOffset(mAlbumItem.getTotalSize());
    }

    @Override
    public void onItemClicked(int position, MediaItem mediaItem) {
        Single.fromCallable(() -> mSerializer.writeItems(getAllItems(), position))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subPosition -> getView().showDetails(subPosition), this::onError);
    }

    public void onLastDetailPosition(int position) {
        int actualPosition = mSerializer.getActualPosition(position);
        getView().setCurrentPosition(actualPosition);
    }

    @Override
    protected void loadMore(int offset) {
        super.loadMore(offset);
        loadMore(offset, this::onSuccess);
    }

    public void loadAll() {
        mIsLoadAll = true;
        if (mOffset < mAlbumItem.getTotalSize()) {
            getView().showAlbumLoadProgress(mOffset, mAlbumItem.getTotalSize());
            mIsAppend = true;
            loadMore(mOffset, this::onPartLoaded);
        }
    }

    protected void onPartLoaded(List<MediaItem> mediaItems) {
        onSuccess(mediaItems);
        getView().updateAlbumProgress(mOffset);

        if (!mIsLoadAll) return;

        if (mOffset < mAlbumItem.getTotalSize()) loadMore(mOffset, this::onPartLoaded);
        else getView().onLoadingCancel();
    }

    public void onCancelLoadAll() {
        mIsLoadAll = false;
    }

    public void onMenuInflate() {
        int layoutType = mPrefs.getMediaListLayout();
        getView().setLayoutMenuItem(layoutType);
    }

    public void onToggleLayout() {
        int layoutType = mPrefs.getMediaListLayout();
        layoutType = layoutType == MediaAdapter.LINEAR ? MediaAdapter.GRID : MediaAdapter.LINEAR;

        mPrefs.setMediaListLayout(layoutType);
        setLayout(layoutType);
    }

    public void onSetLayout() {
        int layoutType = mPrefs.getMediaListLayout();
        setLayout(layoutType);
    }

    private void setLayout(int layoutType) {
        getView().setLayout(layoutType);
        getView().setLayoutMenuItem(layoutType);
    }

    public void onDownloadSelected() {
        checkStoragePermission();
    }

    @Override
    public void onStoragePermissionGranted(int requestCode) {
        startDownload();
    }

    private void startDownload() {
        long taskId = System.currentTimeMillis();
        getView().showTaskIsCreating();

        Completable.fromAction(() -> createTask(taskId))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> onTaskCreated(taskId), MediaListPresenter.this::onError);
    }

    private void onTaskCreated(long taskId) {
        getView().onLoadingCancel();
        getView().startDownload(taskId);
    }

    protected void createTask(long taskId) {
        List<MediaItem> selectedItems = mSelector.getSelectedItems();

        Task task = new Task();
        task.setId(taskId);

        for (int i = 0; i < selectedItems.size(); i++) {
            selectedItems.get(i).setMTaskId(taskId);
        }

        task.setUser(mUserItem.getMainText());
        task.setService(mUserItem.getService());
        task.setAlbum(mAlbumItem.getTitle());
        task.setTotal(selectedItems.size());
        task.setSubPath(getSubPath());

        MediaItem firstItem = selectedItems.get(0);
        task.setFirstFile(DownloadManager.getSavePath(getSubPath(), firstItem));
        task.setType(firstItem.getType());

        mDaoSession.getTaskDao().insert(task);
        mDaoSession.getMediaItemDao().insertOrReplaceInTx(selectedItems);
    }

    private String getSubPath() {
        String downloads = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getPath();
        String appName = "MediaGrub";
        String service = mUserItem.getService();
        String ownerId = mAlbumItem.getOwnerId();
        String albumId = mAlbumItem.getId();

        return String.format(Locale.ENGLISH, "%s/%s/%s/%s/%s",
                downloads, appName, service, ownerId, albumId);
    }
}
