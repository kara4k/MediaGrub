package com.kara4k.mediagrub.other;


import android.content.SharedPreferences;

import com.kara4k.mediagrub.view.adapters.recycler.MediaAdapter;

import javax.inject.Inject;

public class PreferenceManager {

    private static final String MEDIA_LAYOUT = "media_layout";

    private SharedPreferences mSharedPrefs;

    @Inject
    public PreferenceManager(SharedPreferences sharedPrefs) {
        mSharedPrefs = sharedPrefs;
    }

    public void setMediaListLayout(int type) {
        mSharedPrefs.edit().putInt(MEDIA_LAYOUT, type).apply();
    }

    public int getMediaListLayout() {
        return mSharedPrefs.getInt(MEDIA_LAYOUT, MediaAdapter.LINEAR);
    }
}
