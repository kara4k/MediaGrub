package com.kara4k.mediagrub.view.main.settings;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.kara4k.mediagrub.R;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragment {

    private static final String VERSION_KEY = "version";
    private static final String PRIVACY_POLICY = "policy";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setVersionInfo();
        setPrivacyPolicy();
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

    private void setPrivacyPolicy() {
        Preference policyPref = findPreference(PRIVACY_POLICY);

        policyPref.setOnPreferenceClickListener(preference -> {
            getActivity().getBaseContext().startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.privacy_policy_link))));
            return false;
        });

    }


}
