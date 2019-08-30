package com.kara4k.mediagrub.presenter.inst.mappers;


import com.kara4k.mediagrub.model.inst.users.Graphql;
import com.kara4k.mediagrub.model.inst.users.User;
import com.kara4k.mediagrub.model.inst.users.UsersResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserMapper implements Function<UsersResponse, Observable<UserItem>> {

    @Inject
    public UserMapper() {
    }

    @Override
    public Observable<UserItem> apply(final UsersResponse usersResponse) throws Exception {
        return Observable.just(usersResponse)
                .map(UsersResponse::getGraphql)
                .map(Graphql::getUser)
                .map(this::map);
    }

    public UserItem map(final User user) throws Exception {
        final UserItem userItem = new UserItem();
        userItem.setId(user.getId());
        userItem.setMainText(user.getFullName());
        userItem.setAdditionText(user.getUsername());
        userItem.setPhotoUrl(user.getProfilePicUrl());
        userItem.setService(UserItem.INSTAGRAM);
        return userItem;
    }
}
