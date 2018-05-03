package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.presenter.vk.mappers.CustomGroupMapper;
import com.kara4k.mediagrub.presenter.vk.other.VkAuthManager;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VkCustomGroupsPresenter extends VkCustomUsersPresenter {

    @Inject
    CustomGroupMapper mCustomGroupMapper;

    @Inject
    public VkCustomGroupsPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected int getService() {
        return CustomUser.VK;
    }

    @Override
    protected int getUserType() {
        return CustomUser.GROUP;
    }

    @Override
    protected void getUsersInfo(String ids) throws Exception {
        if (ids.equals(EMPTY)) {
            onSuccess(new ArrayList<>());
            return;
        }

        mVkApi.getGroupsInfo(ids, VkAuthManager.getToken())
                .flatMap(mCustomGroupMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onItemClicked(int position, UserItem userItem) {
        String groupId = String.format(Locale.ENGLISH, "-%s", userItem.getId());
        userItem.setId(groupId);
        super.onItemClicked(position, userItem);
    }
}
