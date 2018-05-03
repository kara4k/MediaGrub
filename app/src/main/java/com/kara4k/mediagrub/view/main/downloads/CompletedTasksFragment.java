package com.kara4k.mediagrub.view.main.downloads;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.download.DownloadCompleteCallback;
import com.kara4k.mediagrub.download.DownloadsReceiver;
import com.kara4k.mediagrub.presenter.base.ListPresenter;
import com.kara4k.mediagrub.presenter.main.CompletedTasksPresenter;
import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;
import com.kara4k.mediagrub.view.adapters.recycler.BaseAdapter;
import com.kara4k.mediagrub.view.adapters.recycler.TasksAdapter;
import com.kara4k.mediagrub.view.base.BaseListFragment;

import java.io.File;

import javax.inject.Inject;

public class CompletedTasksFragment extends BaseListFragment<TaskViewObj, TasksAdapter.TaskHolder>
        implements CompletedTasksViewIF {

    @Inject
    CompletedTasksPresenter mPresenter;
    private DownloadsReceiver mReceiver;

    public static CompletedTasksFragment newInstance() {
        Bundle args = new Bundle();
        CompletedTasksFragment fragment = new CompletedTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseAdapter<TaskViewObj, TasksAdapter.TaskHolder> getAdapter() {
        return new TasksAdapter(mPresenter);
    }

    @Override
    protected ListPresenter getListPresenter() {
        return mPresenter;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        setupReceiver();
        getContext().registerReceiver(mReceiver, DownloadsReceiver.getIntentFilter());
        mPresenter.onStart();
    }

    private void setupReceiver() {
        mReceiver = new DownloadsReceiver(new DownloadCompleteCallback() {
            @Override
            public void onTaskComplete(long taskId) {
                if (mPresenter != null) mPresenter.onNewTaskCompleted(taskId);
            }
        });
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectCompletedTasksFragment(this);
    }

    @Override
    public void startActionMode() {
        setModeCallback(new TasksModeCallback());
    }

    @Override
    public void showFolderContent(String firstFile) {
        try {
            File file = new File(firstFile);

            if (file.exists()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.fromFile(file);
                MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

                if (mimeTypeMap.hasExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()))) {
                    String mimeType = mimeTypeMap.getMimeTypeFromExtension(
                            MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
                    intent.setDataAndType(uri, mimeType);
                    startActivity(Intent.createChooser(intent, getString(R.string.intent_chooser_title)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(mReceiver);
    }

   public class TasksModeCallback extends ModeCallback {

        @Override
        protected int getModeMenu() {
            return R.menu.fragment_action_completed_tasks;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_item_action_select_all:
                    getListPresenter().selectAll();
                    break;
                case R.id.menu_item_action_delete:
                    getListPresenter().onDeleteSelected();
                    break;
            }
            return true;
        }
    }
}
