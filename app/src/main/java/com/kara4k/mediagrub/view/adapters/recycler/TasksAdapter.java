package com.kara4k.mediagrub.view.adapters.recycler;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.presenter.main.CompletedTasksPresenter;
import com.kara4k.mediagrub.presenter.main.vo.TaskViewObj;

import java.io.File;

import butterknife.BindView;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TasksAdapter extends BaseAdapter<TaskViewObj, TasksAdapter.TaskHolder> {

    private CompletedTasksPresenter mPresenter;

    public TasksAdapter(CompletedTasksPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_task, parent, false);
        return new TaskHolder(view);
    }

    public class TaskHolder extends BaseHolder<TaskViewObj> implements MaybeObserver<Bitmap> {

        @BindView(R.id.preview_image_view)
        ImageView mPreviewImgView;
        @BindView(R.id.title_text_view)
        TextView mTitleTextView;
        @BindView(R.id.service_text_view)
        TextView mServiceTextView;
        @BindView(R.id.summary_text_view)
        TextView mSummaryTextView;
        @BindView(R.id.total_text_view)
        TextView mTotalTextView;

        public TaskHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(TaskViewObj taskViewObj, int position) {
            super.onBind(taskViewObj, position);
            mTitleTextView.setText(taskViewObj.getTitle());
            mServiceTextView.setText(taskViewObj.getService());
            mSummaryTextView.setText(taskViewObj.getSummary());
            mTotalTextView.setText(String.valueOf(taskViewObj.getTotal()));

            Drawable mediaIcon = getMediaIcon(taskViewObj);
            mTotalTextView.setCompoundDrawablesWithIntrinsicBounds(
                    mediaIcon, null, null, null);

            loadPreview(taskViewObj);
        }

        private Drawable getMediaIcon(TaskViewObj taskViewObj) {
            switch (taskViewObj.getType()) {
                case MediaItem.PHOTO:
                    return mContext.getResources().getDrawable(R.drawable.ic_crop_original_white_18dp);
                case MediaItem.VIDEO:
                    return mContext.getResources().getDrawable(R.drawable.ic_local_movies_white_18dp);
                default:
                    return mContext.getResources().getDrawable(R.drawable.ic_crop_original_white_18dp);
            }
        }

        private void loadPreview(TaskViewObj taskViewObj) {
            try {
                String firstFile = taskViewObj.getFirstFile();
                File file = new File(firstFile);

                if (file.exists()) {
                    if (taskViewObj.getType() == MediaItem.VIDEO) {
                        setVideoPreview(firstFile);
                    } else {
                        setPhotoPreview(firstFile);
                    }
                } else {
                    mPreviewImgView.setImageResource(R.drawable.noalbum);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mPreviewImgView.setImageResource(R.drawable.noalbum);
            }
        }

        private void setPhotoPreview(String firstFile) {
            Maybe.fromCallable(() -> createPhotoThumbnail(firstFile))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }

        private Bitmap createPhotoThumbnail(String firstFile) {
            Bitmap source = BitmapFactory.decodeFile(firstFile);
            return ThumbnailUtils.extractThumbnail(source, 50, 50);
        }

        private void setVideoPreview(String firstFile) {
            Maybe.fromCallable(() -> createVideoThumbnail(firstFile))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }

        private Bitmap createVideoThumbnail(String firstFile) {
            return ThumbnailUtils.createVideoThumbnail(
                    firstFile, MediaStore.Images.Thumbnails.MINI_KIND);
        }

        @Override
        public void onClick(View view) {
            mPresenter.onItemClicked(mItem, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            mPresenter.startActionMode(getAdapterPosition());
            return true;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(Bitmap bitmap) {
            mPreviewImgView.setImageBitmap(bitmap);
        }

        @Override
        public void onError(Throwable e) {
            mPreviewImgView.setImageResource(R.drawable.noalbum);
        }

        @Override
        public void onComplete() {
            mPreviewImgView.setImageResource(R.drawable.noalbum);
        }
    }
}
