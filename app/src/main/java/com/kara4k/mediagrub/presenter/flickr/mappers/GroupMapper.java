package com.kara4k.mediagrub.presenter.flickr.mappers;


import com.kara4k.mediagrub.model.flickr.group.Group;
import com.kara4k.mediagrub.model.flickr.group.GroupResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GroupMapper implements Function<GroupResponse, Observable<UserItem>> {

    private static final String PHOTO_URL_PATTERN = "http://farm%d.staticflickr.com/%s/buddyicons/%s.jpg";
    private static final String ZERO = "0";
    public static final String EMPTY = "";

    @Inject
    public GroupMapper() {
    }

    @Override
    public Observable<UserItem> apply(GroupResponse groupResponse) throws Exception {
        return Observable.just(groupResponse)
                .map(GroupResponse::getGroup)
                .map(this::map);
    }

    private UserItem map(Group group) {
            UserItem userItem = new UserItem();
            userItem.setId(group.getId());
            userItem.setMainText(getMainText(group));
            userItem.setAdditionText(getAddition(group));
            userItem.setPhotoUrl(getPhotoUrl(group));
            userItem.setService(UserItem.FLICKR);
            return userItem;
        }

    private String getPhotoUrl(Group group) {
        String nsid = group.getNsid();
        String iconserver = group.getIconserver();

        if (iconserver.equals(ZERO)) {
            return "https://www.flickr.com/images/buddyicon.gif";
        } else {
            long iconfarm = group.getIconfarm();
            String profilePhotoUrl = String.format(Locale.ENGLISH, PHOTO_URL_PATTERN,
                    iconfarm, iconserver, nsid);
            return profilePhotoUrl;
        }
    }

    private String getAddition(Group group) {
        return group.getPathAlias();
    }

    private String getMainText(Group group) {
        String realName = group.getName().get_content();

        if (realName == null || realName.equals(EMPTY)) {
            return group.getPathAlias();
        }
        return realName;
    }
}
