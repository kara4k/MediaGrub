package com.kara4k.mediagrub.view.base;


import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.AuthPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

abstract public class AuthFragment<P extends AuthPresenter> extends BaseFragment implements AuthViewIF{

    @BindView(R.id.logo)
    ImageView mLogoImgView;
    @BindView(R.id.layout_user)
    View mUserLayout;
    @BindView(R.id.photo_image_view)
    ImageView mPhotoImageView;
    @BindView(R.id.main_text_view)
    TextView mMainTextView;
    @BindView(R.id.addition_text_view)
    TextView mAdditionTextView;
    @BindView(R.id.auth_btn)
    Button mAuthBtn;

    @Inject
    P mPresenter;

    protected abstract int getServiceLogoRes();

    @Override
    protected int getLayout() {
        return R.layout.fragment_auth;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.empty_menu;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        setupViewDefaults();
    }

    private void setupViewDefaults() {
        mLogoImgView.setImageResource(getServiceLogoRes());
        mUserLayout.setVisibility(View.INVISIBLE);
        mUserLayout.findViewById(R.id.num_text_view).setVisibility(View.GONE);
        mUserLayout.findViewById(R.id.divider).setVisibility(View.INVISIBLE);
        mMainTextView.setTextColor(Color.BLACK);
    }

    @OnClick(R.id.auth_btn)
    void onAuthClicked() {
        mPresenter.onAuthClicked();
    }

    @Override
    public void showLogInBtn() {
        mAuthBtn.setText(R.string.button_log_in);
    }

    @Override
    public void showLogOutBtn() {
        mAuthBtn.setText(R.string.button_log_out);
    }

    @Override
    public void showUserDetails(UserItem userItem) {
        mMainTextView.setText(userItem.getMainText());
        mAdditionTextView.setText(userItem.getAdditionText());
        Picasso.with(getContext()).load(userItem.getPhotoUrl()).into(mPhotoImageView);

        mUserLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUserInfo() {
        mUserLayout.setVisibility(View.INVISIBLE);
        showLogInBtn();
    }

    @Override
    public void showAuthDialog() {

    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    public P getPresenter() {
        return mPresenter;
    }
}
