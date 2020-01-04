package com.kara4k.mediagrub.presenter.twitter.mappers;


import com.kara4k.mediagrub.model.twitter.users.User;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
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
    public Observable<UserItem> apply(final List<User> userList) throws Exception {
        return Observable.fromIterable(userList)
                .map(this::map);
    }

    public UserItem map(final User user) throws Exception {
        final UserItem userItem = new UserItem();
        userItem.setId(user.getScreenName());
        userItem.setMainText(user.getName());
        userItem.setAdditionText(user.getScreenName());
        userItem.setPhotoUrl(getPhotoUrl(user));
        userItem.setService(UserItem.TWITTER);
        return userItem;
    }

    public Function<String, List<UserItem>> mapSearchedUsers() {
        return page -> {
            final Document document = Jsoup.parse(page);
            final Elements items = document.getElementsByClass("js-stream-item");

            return parseUsersInfo(items);
        };
    }

    // TODO: 1/4/20 sad. find api workaround
    private List<UserItem> parseUsersInfo(final Elements userElements) {
        final List<UserItem> users = new ArrayList<>(userElements.size());

        for (final Element x : userElements) {
            final String main = x.select("a.fullname").text();
            final String addition = x.select("b.u-linkComplex-target").text();
            final String photo = x.select("img.ProfileCard-avatarImage").attr("src");
            final Elements privateElems = x.select("span.Icon.Icon--protected");

            final UserItem userItem = new UserItem();
            userItem.setId(addition);
            userItem.setMainText(main);
            userItem.setAdditionText(addition);
            userItem.setPhotoUrl(photo);
            userItem.setPrivate(!privateElems.isEmpty());

            users.add(userItem);
        }

        return users;
    }


    private String getPhotoUrl(final User user) {
        return String.format(Locale.ENGLISH, PHOTO_URL_TEMPLATE, user.getScreenName());
    }
}
