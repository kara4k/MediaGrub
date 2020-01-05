package com.kara4k.mediagrub.view.adapters.recycler;

import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public abstract class AbstractUserHolder extends BaseHolder<UserItem> {

    @BindView(R.id.main_text_view)
    TextView mMainTextView;
    @BindView(R.id.addition_text_view)
    TextView mAdditionTextView;
    @BindView(R.id.photo_image_view)
    ImageView mPhotoImageView;
    @BindView(R.id.num_text_view)
    TextView mNumTextView;
    @BindView(R.id.icon_private_user)
    ImageView mPrivateUserIcon;


    public AbstractUserHolder(final View itemView) {
        super(itemView);
    }

    @CallSuper
    public void onBind(final UserItem userItem, final int position) {
        super.onBind(userItem, position);
        mMainTextView.setText(userItem.getMainText());
        mAdditionTextView.setText(userItem.getAdditionText());

        if (StringUtils.isNotEmpty(userItem.getPhotoUrl())){
            Picasso.with(mContext)
                    .load(userItem.getPhotoUrl())
                    .error(R.drawable.ic_photo_error_white_50dp)
                    .transform(new CropCircleTransformation())
                    .into(mPhotoImageView);
        }

        mNumTextView.setText(String.valueOf(position));
        mPrivateUserIcon.setVisibility(userItem.isPrivate() ? View.VISIBLE : View.GONE);
    }

}
