package com.kara4k.mediagrub.view.base;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.AppComponent;
import com.kara4k.mediagrub.other.App;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public static final int EMPTY = -1;

    protected abstract int getContentView();

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        injectDaggerDependencies();
        onViewReady();
    }

    protected void setNoAnimatedFragment(final Fragment fragment) {
        changeFragment(fragment, false, false);
    }

    protected void setFragment(final Fragment fragment) {
        getSupportFragmentManager()
                .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        changeFragment(fragment, false, true);
    }

    public void addFragment(final Fragment fragment) {
        changeFragment(fragment, true, true);
    }

    private void changeFragment(final Fragment fragment, final boolean isBackStack, final boolean isShowAnimation) {
        try {
            final FragmentManager sfm = getSupportFragmentManager();
            final int container = R.id.fragment_container;

            final Fragment currentFrag = sfm.findFragmentById(container);
            final FragmentTransaction transaction = sfm.beginTransaction();

            if (isShowAnimation) {
                transaction.setCustomAnimations(R.anim.from_in, R.anim.to_in,
                        R.anim.from_out, R.anim.to_out);
            }

            if (currentFrag == null) {
                transaction.add(container, fragment);
            } else {
                transaction.replace(container, fragment);

                if (isBackStack) transaction.addToBackStack(null);
            }

            transaction.commit();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(final String title, final String subtitle) {
        final ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
            supportActionBar.setSubtitle(subtitle);
        }
    }

    protected void showConfirmDialog(final String title, final String text, final DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, null)
                .create().show();
    }

    protected void showItemsDialog(final String title, final CharSequence[] items,
                                   final DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(items, listener)
                .create()
                .show();
    }

    protected void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void hideSoftKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                final InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager
                        .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    protected void activityStart(final Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.from_in, R.anim.to_in);
    }

    protected void activityStartForResult(final Intent intent, final int code) {
        startActivityForResult(intent, code);
        overridePendingTransition(R.anim.from_in, R.anim.to_in);
    }

    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    protected void injectDaggerDependencies() {
    }

    protected void onViewReady() {
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.from_out, R.anim.to_out);
    }
}
