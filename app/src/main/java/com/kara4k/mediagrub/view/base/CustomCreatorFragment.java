package com.kara4k.mediagrub.view.base;


import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.CustomCreatorPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomCreatorFragment<P extends CustomCreatorPresenter> extends BaseFragment
        implements CustomCreatorIF {

    @BindView(R.id.input_edit_text)
    EditText mEditText;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.layout_user)
    View mUserLayout;
    @BindView(R.id.photo_image_view)
    ImageView mPhotoImageView;
    @BindView(R.id.main_text_view)
    TextView mMainTextView;
    @BindView(R.id.addition_text_view)
    TextView mAdditionTextView;
    @BindView(R.id.icon_private_user)
    ImageView mPrivateImageView;
    @BindView(R.id.add_btn)
    Button mAddBtn;

    @Inject
    P mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_custom_creator;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.empty_menu;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        setupView();
    }

    private void setupView() {
        mUserLayout.findViewById(R.id.num_text_view).setVisibility(View.GONE);
        mUserLayout.findViewById(R.id.divider).setVisibility(View.INVISIBLE);
        mMainTextView.setTextColor(Color.BLACK);
    }

    @OnClick(R.id.search_btn)
    void onSearchClicked() {
        mPresenter.onSearch(mEditText.getText().toString());
    }

    @OnClick(R.id.add_btn)
    void onAddClicked() {
        mPresenter.onAddClicked();
    }

    @Override
    public void showUserDetails(final UserItem userItem) {
        mMainTextView.setText(userItem.getMainText());
        mAdditionTextView.setText(userItem.getAdditionText());
        Picasso.with(getContext()).load(userItem.getPhotoUrl()).into(mPhotoImageView);
        mPrivateImageView.setVisibility(userItem.isPrivate() ? View.VISIBLE : View.GONE);
        mUserLayout.setVisibility(View.VISIBLE);
        mAddBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserAdded() {
        showToast(getString(R.string.message_success_added));
    }

    @Override
    public void showUserAddedButPrivate() {
        showToast(getString(R.string.message_success_added_but_private));
    }

    @Override
    public void showUserAlreadyExists() {
        showToast(getString(R.string.message_already_exists));
    }

    @Override
    public void hideUserInfo() {
        mUserLayout.setVisibility(View.INVISIBLE);
        mAddBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideKeyboard() {
        hideSoftKeyboard();
    }

    @Override
    public void showError(final String message) {
        try {
            showToast(message == null ? getString(R.string.custom_user_not_found) : message);
        } catch (final Exception e) {
            // TODO: 9/16/19 android 9 debug (context issues here)
        }
    }
}
