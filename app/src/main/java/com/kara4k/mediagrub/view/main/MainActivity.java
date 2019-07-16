package com.kara4k.mediagrub.view.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.other.AdManager;
import com.kara4k.mediagrub.other.RateManager;
import com.kara4k.mediagrub.presenter.main.MainPresenter;
import com.kara4k.mediagrub.view.base.ServiceNavigation;
import com.kara4k.mediagrub.view.flickr.FlickrServiceNavigation;
import com.kara4k.mediagrub.view.inst.InstServiceNavigation;
import com.kara4k.mediagrub.view.main.downloads.TasksNavigation;
import com.kara4k.mediagrub.view.main.settings.SettingsActivity;
import com.kara4k.mediagrub.view.tumblr.TumblrServiceNavigation;
import com.kara4k.mediagrub.view.twitter.TwitterServiceNavigation;
import com.kara4k.mediagrub.view.vk.VkServiceNavigation;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends DrawerActivity implements MainViewIF {

    private static final int AUTH_REQUEST = 1;

    @BindView(R.id.bottom_toolbar)
    ActionMenuView mBottomToolbar;

    @Inject
    MainPresenter mPresenter;
    private RateManager mRateManager;
    private AdManager mAdManager;

    private ServiceNavigation mServiceNavigation;
    private Menu mBottomMenu;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectMainActivity(this);
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        initRateManager();
        startFirebase();
        initAdManager();
        setupBottomMenu();
        mPresenter.onCreate();
    }

    private void initAdManager() {
        mAdManager = AdManager.getInstance(this);
    }

    private void initRateManager() {
        mRateManager = new RateManager(this);
        mRateManager.checkForRateOffer();
    }

    private void startFirebase() {
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
    }

    @Override
    public void showService(ServiceNavigation serviceNavigation) {
        mServiceNavigation = serviceNavigation;
        setFragment(mServiceNavigation.getMainFragment());
        mBottomToolbar.setVisibility(serviceNavigation.getToolbarVisibility());
//        mAdManager.checkShowAd(); todo add maybe?
    }

    @Override
    public void setNavItemChecked(VkServiceNavigation service) {
        try {
            mNavigationView.getMenu().findItem(service.getMenuItemId()).setChecked(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupBottomMenu() {
        mBottomMenu = mBottomToolbar.getMenu();
        getMenuInflater().inflate(R.menu.bottom_bar_menu, mBottomMenu);
        mBottomToolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    @Override
    protected void onNavItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_item_vk:
                showService(VkServiceNavigation.getInstance());
                break;
            case R.id.nav_menu_item_inst:
                showService(InstServiceNavigation.getInstance());
                break;
            case R.id.nav_menu_item_twitter:
                showService(TwitterServiceNavigation.getInstance());
                break;
            case R.id.nav_menu_item_tumblr:
                showService(TumblrServiceNavigation.getInstance());
                break;
            case R.id.nav_menu_item_flickr:
                showService(FlickrServiceNavigation.getInstance());
                break;
            case R.id.nav_menu_item_tasks:
                showService(TasksNavigation.getInstance());
                break;
            case R.id.nav_menu_item_rate:
                mRateManager.rateApp();
                break;
            case R.id.nav_menu__item_share:
                mRateManager.shareApp();
                break;
            case R.id.nav_menu_item_about:
                activityStart(SettingsActivity.newIntent(this));
                break;
        }
        mNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setBottomToolbarVisibility();
        return super.onCreateOptionsMenu(menu);
    }

    private void setBottomToolbarVisibility() {
        if (mBottomMenu != null) {
            mBottomToolbar.setVisibility(mServiceNavigation.getToolbarVisibility());
            mServiceNavigation.onUpdateMenu(mBottomMenu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        mAdManager.checkShowAd();

        switch (item.getItemId()) {
            case R.id.service_item_auth:
                Intent intent = mServiceNavigation.getAuthIntent(this);
                activityStartForResult(intent, AUTH_REQUEST);
                return true;
            case R.id.service_item_friends:
                setFragment(mServiceNavigation.getFriends());
                return true;
            case R.id.service_item_custom_friends:
                setFragment(mServiceNavigation.getCustomFriends());
                return true;
            case R.id.service_item_search:
                setFragment(mServiceNavigation.getSearch());
                return true;
            case R.id.service_item_own:
                setFragment(mServiceNavigation.getOwn());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_REQUEST) setFragment(mServiceNavigation.getMainFragment());
    }

    @Override
    public void setDrawerMode(int lockMode) {
        super.setDrawerMode(lockMode);
        if (lockMode == DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mBottomToolbar.setVisibility(View.GONE);
        } else {
            setBottomToolbarVisibility();
        }
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed(getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void showExitConfirm() {
        String message = getString(R.string.dialog_exit_message);
        DialogInterface.OnClickListener onOkListener = (dialogInterface, i)
                -> super.onBackPressed();

        showConfirmDialog(null, message, onOkListener);
    }

    @Override
    public void letTheGreatSuperBackPressMethodDoItsWorkLol() {
        super.onBackPressed();
    }

}
