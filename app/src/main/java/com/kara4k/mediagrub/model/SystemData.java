package com.kara4k.mediagrub.model;


import android.content.Context;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SystemData {

    private static final String WALL_ID = "-7";
    private static final String PROFILE_ID = "-6";
    public static final String UNDEFINED_SIZE = "---";
    public static final String EMPTY_ALBUM_COVER = "https://vk.com/images/m_noalbum.png";

    private Context mContext;

    @Inject
    public SystemData(Context context) {
        mContext = context;
    }

    public List<AlbumItem> getSystemAlbums(String ownerId) {
        List<AlbumItem> systemAlbums = new ArrayList<>(2);
        AlbumItem profileAlbum = createAlbum(ownerId, PROFILE_ID,
                mContext.getString(R.string.vk_system_album_profile));
        AlbumItem wallAlbum = createAlbum(ownerId, WALL_ID,
                mContext.getString(R.string.vk_system_album_wall));

        systemAlbums.add(profileAlbum);
        systemAlbums.add(wallAlbum);
        return systemAlbums;
    }

    private AlbumItem createAlbum(String ownerId, String wall, String title) {
        AlbumItem albumItem = new AlbumItem();
        albumItem.setId(wall);
        albumItem.setOwnerId(ownerId);
        albumItem.setSize(UNDEFINED_SIZE);
        albumItem.setTitle(title);
        albumItem.setDescription("");
        albumItem.setCoverUrl(EMPTY_ALBUM_COVER);
        return albumItem;
    }

    public UserItem createSearchUser(String service) {
        UserItem userItem = new UserItem();
        userItem.setService(service);
        userItem.setMainText(mContext.getString(R.string.user_search_name));
        return userItem;
    }

    public AlbumItem createSearchAlbum() {
        AlbumItem albumItem = new AlbumItem();
        albumItem.setOwnerId(mContext.getString(R.string.user_search_name));
        albumItem.setSize(SystemData.UNDEFINED_SIZE);
        return albumItem;
    }
}
