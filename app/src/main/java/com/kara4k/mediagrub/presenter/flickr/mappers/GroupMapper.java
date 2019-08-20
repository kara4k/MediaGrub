package com.kara4k.mediagrub.presenter.flickr.mappers;


import com.kara4k.mediagrub.model.flickr.group.Group;
import com.kara4k.mediagrub.model.flickr.group.GroupResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GroupMapper implements Function<GroupResponse, Observable<UserItem>> {

    private static final String PHOTO_URL_PATTERN = "https://farm%d.staticflickr.com/%s/buddyicons/%s.jpg";
    private static final String ZERO = "0";
    public static final String EMPTY = "";

    @Inject
    public GroupMapper() {
    }

    @Override
    public Observable<UserItem> apply(final GroupResponse groupResponse) throws Exception {
        return Observable.just(groupResponse)
                .map(GroupResponse::getGroup)
                .map(this::map);
    }

    private UserItem map(final Group group) {
            final UserItem userItem = new UserItem();
            userItem.setId(group.getId());
            userItem.setMainText(getMainText(group));
            userItem.setAdditionText(getAddition(group));
            userItem.setPhotoUrl(getPhotoUrl(group));
            userItem.setService(UserItem.FLICKR);
            return userItem;
        }

    private String getPhotoUrl(final Group group) {
        final String nsid = group.getNsid();
        final String iconserver = group.getIconserver();

        if (iconserver.equals(ZERO)) {
            return "https://www.flickr.com/images/buddyicon.gif";
        } else {
            final long iconfarm = group.getIconfarm();
            final String profilePhotoUrl = String.format(Locale.ENGLISH, PHOTO_URL_PATTERN,
                    iconfarm, iconserver, nsid);
            return profilePhotoUrl;
        }
    }

    private String getAddition(final Group group) {
        return group.getPathAlias();
    }

    private String getMainText(final Group group) {
        final String realName = group.getName().get_content();

        if (realName == null || realName.equals(EMPTY)) {
            return group.getPathAlias();
        }
        return realName;
    }
}
