package com.kara4k.mediagrub.presenter.inst.mappers;


import com.kara4k.mediagrub.model.inst.users.User;
import com.kara4k.mediagrub.model.inst.users.UsersResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

public class UserMapper {

    @Inject
    public UserMapper() {
    }

    public Observable<UserItem> apply(final UsersResponse usersResponse,
                                      final String username) throws Exception {

        return Observable.just(usersResponse)
                .map(UsersResponse::getUsers)
                .flatMapIterable(users -> users)
                .map(User::getUser)
                .filter(getFilter(username))
                .map(this::map);
    }

    public UserItem map(final User user) throws Exception {
        final UserItem userItem = new UserItem();
        userItem.setId(user.getPk());
        userItem.setMainText(user.getFullName());
        userItem.setAdditionText(user.getUsername());
        userItem.setPhotoUrl(user.getProfilePicUrl());
        userItem.setService(UserItem.INSTAGRAM);
        userItem.setPrivate(user.getIsPrivate());
        return userItem;
    }

    private Predicate<User> getFilter(final String username) {
        return user -> user.getUsername().equals(username);
    }

}
