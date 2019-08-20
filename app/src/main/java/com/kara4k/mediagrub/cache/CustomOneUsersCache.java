package com.kara4k.mediagrub.cache;

import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.HashMap;
import java.util.Map;

public class CustomOneUsersCache {

    private static CustomOneUsersCache customOneUsersCache;

    private CustomOneUsersCache() {
    }

    public static CustomOneUsersCache getInstance() {
        if (customOneUsersCache == null) {
            customOneUsersCache = new CustomOneUsersCache();
        }
        return customOneUsersCache;
    }

    private final Map<String, UserItem> usersCache = new HashMap<>();

    public void putIfNotAlreadyExists(final UserItem userItem) {
        if (!usersCache.containsKey(createKey(userItem))) {
            usersCache.put(createKey(userItem), userItem);
        }
    }

    public UserItem getOrNull(final CustomUser customUser) {
        return usersCache.get(createKey(customUser));
    }

    private String createKey(final UserItem userItem) {
        switch (userItem.getService()) {
            case UserItem.INSTAGRAM:
                return UserItem.INSTAGRAM.concat(userItem.getAdditionText());
            case UserItem.FLICKR:
                return UserItem.FLICKR.concat(userItem.getId());
            case UserItem.TUMBLR:
                return UserItem.TUMBLR.concat(userItem.getId());
            default:
                return null;
        }
    }

    private String createKey(final CustomUser customUser) {
        switch (customUser.getService()) {
            case CustomUser.INSTAGRAM:
                return UserItem.INSTAGRAM.concat(customUser.getKey());
            case CustomUser.FLICKR:
                return UserItem.FLICKR.concat(customUser.getKey());
            case CustomUser.TUMBLR:
                return UserItem.TUMBLR.concat(customUser.getKey());
            default:
                return null;
        }
    }
}
