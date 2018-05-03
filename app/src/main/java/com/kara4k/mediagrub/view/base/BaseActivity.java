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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        injectDaggerDependencies();
        onViewReady();
    }

    protected void setNoAnimatedFragment(Fragment fragment){
        changeFragment(fragment, false, false);
    }

    protected void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        changeFragment(fragment, false, true);
    }

    public void addFragment(Fragment fragment) {
        changeFragment(fragment, true, true);
    }

    private void changeFragment(Fragment fragment, boolean isBackStack, boolean isShowAnimation) {
        try {
            FragmentManager sfm = getSupportFragmentManager();
            int container = R.id.fragment_container;

            Fragment currentFrag = sfm.findFragmentById(container);
            FragmentTransaction transaction = sfm.beginTransaction();

            if (isShowAnimation) {
                transaction.setCustomAnimations(R.anim.from_in, R.anim.to_in,
                        R.anim.from_out,  R.anim.to_out);
            }

            if (currentFrag == null) {
                transaction.add(container, fragment);
            } else {
                transaction.replace(container, fragment);

                if (isBackStack) transaction.addToBackStack(null);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String title, String subtitle) {
        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
            supportActionBar.setSubtitle(subtitle);
        }
    }

    protected void showConfirmDialog(String title, String text, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, null)
                .create().show();
    }

    protected void showItemsDialog(String title, CharSequence[] items,
                                   DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(items, listener)
                .create()
                .show();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void hideSoftKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager
                        .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void activityStart(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.from_in, R.anim.to_in);
    }

    protected void activityStartForResult(Intent intent, int code) {
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
