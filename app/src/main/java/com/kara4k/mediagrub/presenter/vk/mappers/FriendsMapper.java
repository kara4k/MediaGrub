package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.friends.FriendsResponse;
import com.kara4k.mediagrub.model.vk.friends.Item;
import com.kara4k.mediagrub.model.vk.friends.Response;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FriendsMapper implements Function<FriendsResponse, Observable<UserItem>> {

    @Inject
    public FriendsMapper() {
    }

    @Override
    public Observable<UserItem> apply(FriendsResponse friendsResponse) {
        return Observable.just(friendsResponse)
                .map(FriendsResponse::getResponse)
                .flatMapIterable(Response::getItems)
                .map(this::mapFriend);
    }

    private UserItem mapFriend(Item item) {
        UserItem userItem = new UserItem();
        String id = String.valueOf(item.getId());
        String mainText = String.format("%s %s", item.getFirstName(), item.getLastName());

        userItem.setId(id);
        userItem.setMainText(mainText);
        userItem.setAdditionText(id);
        userItem.setPhotoUrl(item.getPhoto100());
        userItem.setService(UserItem.VKONTAKTE);
        return userItem;
    }
}
