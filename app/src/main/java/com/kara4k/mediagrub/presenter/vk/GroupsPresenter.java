package com.kara4k.mediagrub.presenter.vk;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.presenter.base.UsersListPresenter;
import com.kara4k.mediagrub.presenter.vk.mappers.GroupsMapper;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GroupsPresenter extends UsersListPresenter {

    @Inject
    VkApi mVkApi;
    @Inject
    GroupsMapper mGroupsMapper;

    @Inject
    public GroupsPresenter() {
    }

    @Override
    public void onStart() {
        if (VKSdk.isLoggedIn()) {
            super.onStart();
            String token = VKAccessToken.currentToken().accessToken;
            getGroups(token);
        } else {
            getView().setItems(new ArrayList<>());
            getView().showHint();
        }
    }

    private void getGroups(String token) {
        mVkApi.getGroups(token).flatMap(mGroupsMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onItemClicked(int position, UserItem userItem) {
        String groupId = String.format(Locale.ENGLISH, "-%s", userItem.getId());
        userItem.setId(groupId);

        getView().showAlbums(userItem);
    }
}
