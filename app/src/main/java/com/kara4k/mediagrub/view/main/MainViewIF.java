package com.kara4k.mediagrub.view.main;


import com.kara4k.mediagrub.view.base.ServiceNavigation;
import com.kara4k.mediagrub.view.base.ViewIF;
import com.kara4k.mediagrub.view.vk.VkServiceNavigation;

public interface MainViewIF extends ViewIF {

    void showService(ServiceNavigation serviceNavigation);

    void showExitConfirm();

    void letTheGreatSuperBackPressMethodDoItsWorkLol();

    void setNavItemChecked(VkServiceNavigation service);
}
