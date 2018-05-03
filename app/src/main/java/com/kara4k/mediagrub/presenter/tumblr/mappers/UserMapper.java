package com.kara4k.mediagrub.presenter.tumblr.mappers;


import com.kara4k.mediagrub.model.tumblr.user.Blog;
import com.kara4k.mediagrub.model.tumblr.user.Response;
import com.kara4k.mediagrub.model.tumblr.user.UserResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserMapper implements Function<UserResponse, Observable<UserItem>> {

    private static final String PHOTO_URL_TEMPLATE = "https://api.tumblr.com/v2/blog/%s.tumblr.com/avatar/128";

    @Inject
    public UserMapper() {
    }

    @Override
    public Observable<UserItem> apply(UserResponse userResponse) throws Exception {
        return Observable.just(userResponse)
                .map(UserResponse::getResponse)
                .map(Response::getBlog)
                .map(this::map);
    }

    private UserItem map(Blog blog) {
        UserItem userItem = new UserItem();
        userItem.setId(blog.getName());
        userItem.setMainText(blog.getTitle());
        userItem.setAdditionText(blog.getName());
        userItem.setPhotoUrl(getPhotoUrl(blog));
        userItem.setService(UserItem.TUMBLR);
        return userItem;
    }

    private String getPhotoUrl(Blog blog) {
        return String.format(Locale.ENGLISH, PHOTO_URL_TEMPLATE, blog.getName());
    }
}
