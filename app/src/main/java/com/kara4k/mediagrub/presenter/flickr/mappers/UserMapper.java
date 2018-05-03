package com.kara4k.mediagrub.presenter.flickr.mappers;


import com.kara4k.mediagrub.model.flickr.user.Person;
import com.kara4k.mediagrub.model.flickr.user.UserResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserMapper implements Function<UserResponse, Observable<UserItem>> {

    private static final String PHOTO_URL_PATTERN = "http://farm%d.staticflickr.com/%s/buddyicons/%s.jpg";
    private static final String ZERO = "0";
    private static final String EMPTY = "";

    @Inject
    public UserMapper() {
    }

    @Override
    public Observable<UserItem> apply(UserResponse userResponse) throws Exception {
        return Observable.just(userResponse)
                .map(UserResponse::getPerson)
                .map(this::map);
    }

    private UserItem map(Person person) {
        UserItem userItem = new UserItem();
        userItem.setId(person.getId());
        userItem.setMainText(getMainText(person));
        userItem.setAdditionText(getAddition(person));
        userItem.setPhotoUrl(getPhotoUrl(person));
        userItem.setService(UserItem.FLICKR);
        return userItem;
    }

    private String getPhotoUrl(Person person) {
        String nsid = person.getNsid();
        String iconserver = person.getIconserver();

        if (iconserver.equals(ZERO)) {
            return "https://www.flickr.com/images/buddyicon.gif";
        } else {
            long iconfarm = person.getIconfarm();
            String profilePhotoUrl = String.format(Locale.ENGLISH, PHOTO_URL_PATTERN,
                    iconfarm, iconserver, nsid);
            return profilePhotoUrl;
        }
    }

    private String getAddition(Person person) {
        return person.getUsername().get_content();
    }

    private String getMainText(Person person) {
        String realName = person.getRealname().get_content();

        if (realName == null || realName.equals(EMPTY)) {
            return person.getUsername().get_content();
        }
        return realName;
    }
}
