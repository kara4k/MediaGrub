package com.kara4k.mediagrub.presenter.twitter.mappers;


import com.kara4k.mediagrub.model.twitter.users.User;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UsersMapper implements Function<List<User>, Observable<UserItem>> {

    private static final String PHOTO_URL_TEMPLATE = "https://twitter.com/%s/profile_image?size=bigger";

    @Inject
    public UsersMapper() {
    }

    @Override
    public Observable<UserItem> apply(List<User> userList) throws Exception {
        return Observable.fromIterable(userList)
                .map(this::map);
    }

    public UserItem map(User user) throws Exception {
        UserItem userItem = new UserItem();
        userItem.setId(user.getScreenName());
        userItem.setMainText(user.getName());
        userItem.setAdditionText(user.getScreenName());
        userItem.setPhotoUrl(getPhotoUrl(user));
        userItem.setService(UserItem.TWITTER);
        return userItem;
    }

    private String getPhotoUrl(User user) {
        return String.format(Locale.ENGLISH, PHOTO_URL_TEMPLATE, user.getScreenName());
    }
}
