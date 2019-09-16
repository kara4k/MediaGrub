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
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setVersionInfo();
        setPrivacyPolicy();
    }

    private void setVersionInfo() {
        final Preference versionPref = findPreference(VERSION_KEY);
        try {
            final PackageInfo packageInfo = getActivity().getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0);
            final String versionName = packageInfo.versionName;
            final int versionCode = packageInfo.versionCode;
            final String summary = String.format(Locale.ENGLISH, "%s (%d)",
                    versionName, versionCode);

            versionPref.setSummary(summary);
        } catch (final PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPrivacyPolicy() {
        final Preference policyPref = findPreference(PRIVACY_POLICY);

        policyPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(final Preference preference) {
                getActivity().getBaseContext().startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.privacy_policy_link))));

                return false;
            }
        });

    }


}
