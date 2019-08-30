package com.kara4k.mediagrub.view.base;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.AppComponent;
import com.kara4k.mediagrub.other.App;
import com.kara4k.mediagrub.view.adapters.ScreenConfig;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    public static final int PORTRAIT = 1;
    public static final int LANDSCAPE = 2;
    public static final int UNDEFINED = -1;

    private ProgressDialog mProgressDialog;

    protected abstract int getLayout();

    protected abstract int getMenuRes();

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        injectDaggerDependencies();
        onFragmentCreate();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        onViewReady();
        return view;
    }

    protected void onFragmentCreate() {
    }

    protected void injectDaggerDependencies() {
    }

    protected void onViewReady() {
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(getMenuRes(), menu);
        onMenuInflated(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    protected void onMenuInflated(final Menu menu) {
    }

    protected void showToast(final String message) {
        try {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    protected void showConfirmDialog(final String title, final String text, final DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, null)
                .create().show();
    }

    protected AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).getAppComponent();
    }

    protected void addFragment(final Fragment fragment) {
        ((BaseActivity) getActivity()).addFragment(fragment);
    }

    protected void setTitle(final String title, final String subtitle) {
        try {
            final ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle(title);
                supportActionBar.setSubtitle(subtitle);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    protected void toggleActionBar() {
        try {
            final AppCompatActivity activity = (AppCompatActivity) getActivity();
            final ActionBar supportActionBar = activity.getSupportActionBar();
            if (supportActionBar != null && supportActionBar.isShowing()) {
                supportActionBar.hide();
            } else if (supportActionBar != null && !supportActionBar.isShowing()) {
                supportActionBar.show();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    protected void activityStart(final Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.from_in, R.anim.to_in);
    }

    protected void activityStartForResult(final Intent intent, final int code) {
        startActivityForResult(intent, code);
        getActivity().overridePendingTransition(R.anim.from_in, R.anim.to_in);
    }

    protected ScreenConfig getScreenConfig() {
        final Point screenSize = getScreenSize();
        final int orientation = getOrientation();
        return new ScreenConfig(screenSize, orientation);
    }

    protected Point getScreenSize() {
        final Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    protected int getOrientation() {
        final int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();

        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) return PORTRAIT;

        return LANDSCAPE;
    }

    protected void showLoadingDialog(final String title, final String message, final int progress, final int max, final boolean isCancelable) {
        final DialogInterface.OnClickListener onCancel = (dialogInterface, i) -> onProgressDialogCancel();

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);

        if (isCancelable) {
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                    getString(R.string.dialog_button_cancel), onCancel);
        }

        if (progress != UNDEFINED && max != UNDEFINED) {
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(max);
            mProgressDialog.setProgress(progress);
        } else {
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void updateDialogProgress(final int progress) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.setProgress(progress);
        }
    }

    protected void onProgressDialogCancel() {
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void hideSoftKeyboard() {
        ((BaseActivity) getActivity()).hideSoftKeyboard();
    }

}

