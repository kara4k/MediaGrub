package com.kara4k.mediagrub.view.main.settings;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.kara4k.mediagrub.R;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragment {

    private static final String VERSION_KEY = "version";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setVersionInfo();
    }

    private void setVersionInfo() {
        Preference versionPref = findPreference(VERSION_KEY);
        try {
            PackageInfo packageInfo = getActivity().getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0);
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;
            String summary = String.format(Locale.ENGLISH, "%s (%d)",
                    versionName, versionCode);

            versionPref.setSummary(summary);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


}
