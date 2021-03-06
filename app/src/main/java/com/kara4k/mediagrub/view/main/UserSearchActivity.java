package com.kara4k.mediagrub.view.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.view.base.BaseActivity;
import com.kara4k.mediagrub.view.inst.search.InstUserSearchFragment;
import com.kara4k.mediagrub.view.twitter.TwitterUserSearchFragment;

public class UserSearchActivity extends BaseActivity {

    public static final String SERVICE = "service";
    public static final String TYPE = "type";

    @Override
    protected void onViewReady() {
        super.onViewReady();
        final int service = getIntent().getIntExtra(SERVICE, EMPTY);
        final int type = getIntent().getIntExtra(TYPE, EMPTY);

        final Fragment fragment = getFragment(service, type);
        setNoAnimatedFragment(fragment);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_container;
    }

    private Fragment getFragment(final int service, final int type) {
        switch (service) {
            case CustomUser.INSTAGRAM:
                return InstUserSearchFragment.newInstance();
            case CustomUser.TWITTER:
                return TwitterUserSearchFragment.newInstance();
            default:
                return new Fragment();
        }
    }

    public static Intent newIntent(final Context context, final int service, final int type) {
        final Intent intent = new Intent(context, UserSearchActivity.class);
        intent.putExtra(SERVICE, service);
        intent.putExtra(TYPE, type);
        return intent;
    }
}
