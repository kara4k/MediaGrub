package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.groups.GroupsResponse;
import com.kara4k.mediagrub.model.vk.groups.Item;
import com.kara4k.mediagrub.model.vk.groups.Response;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GroupsMapper implements Function<GroupsResponse, Observable<UserItem>> {

    @Inject
    public GroupsMapper() {
    }

    @Override
    public Observable<UserItem> apply(GroupsResponse groupsResponse) throws Exception {
        return Observable.just(groupsResponse)
                .filter(this::filterResponseError)
                .map(GroupsResponse::getResponse)
                .map(Response::getItems)
                .flatMapIterable(items -> items)
                .map(this::mapGroup);

    }

    private boolean filterResponseError(GroupsResponse response) throws Exception {
        if (response.getResponse() == null && response.getResponseError() != null) {
            String errorMsg = response.getResponseError().getErrorMsg();

            throw new Exception(errorMsg);
        }
        return true;
    }

    private UserItem mapGroup(Item item) {
        UserItem userItem = new UserItem();
        String id = String.valueOf(item.getId());
        String mainText = item.getName();
        String additionText = item.getScreenName();
        String photoUrl = item.getPhoto100();

        userItem.setId(id);
        userItem.setMainText(mainText);
        userItem.setAdditionText(additionText);
        userItem.setPhotoUrl(photoUrl);
        userItem.setService(UserItem.VKONTAKTE);
        return userItem;
    }

}
