package com.kara4k.mediagrub.presenter.main;


import com.kara4k.mediagrub.presenter.base.Presenter;
import com.kara4k.mediagrub.view.main.MainViewIF;
import com.kara4k.mediagrub.view.vk.VkServiceNavigation;

import javax.inject.Inject;

public class MainPresenter extends Presenter {

    @Inject
    MainViewIF mView;

    @Inject
    public MainPresenter() {
    }

    public void onCreate() {
        VkServiceNavigation service = VkServiceNavigation.getInstance();
        mView.showService(service);
        mView.setNavItemChecked(service);
    }

    public void onBackPressed(int backStackEntryCount) {
        if(backStackEntryCount == 0) {
            mView.showExitConfirm();
        } else {
            mView.letTheGreatSuperBackPressMethodDoItsWorkLol();
        }
    }
}
