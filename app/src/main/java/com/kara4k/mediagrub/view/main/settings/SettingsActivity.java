package com.kara4k.mediagrub.view.main.settings;


import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;

import com.kara4k.mediagrub.R;

import java.util.List;

public class SettingsActivity extends PreferenceActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
        intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT,
                SettingsFragment.class.getName());
        return intent;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.settings_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.from_out, R.anim.to_out);
    }
}
