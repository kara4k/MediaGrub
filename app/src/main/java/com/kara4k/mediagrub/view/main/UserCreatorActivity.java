package com.kara4k.mediagrub.view.main;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.view.base.BaseActivity;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomGroupsCreatorFragment;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.inst.custom.InstCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.tumblr.custom.TumblrCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.twitter.custom.TwitterCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.vk.custom.VkCustomGroupCreatorFragment;
import com.kara4k.mediagrub.view.vk.custom.VkCustomUsersCreatorFragment;

public class UserCreatorActivity extends BaseActivity {

    public static final String SERVICE = "service";
    public static final String TYPE = "type";

    @Override
    protected int getContentView() {
        return R.layout.activity_container;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        final int service = getIntent().getIntExtra(SERVICE, EMPTY);
        final int type = getIntent().getIntExtra(TYPE, EMPTY);

        final Fragment fragment = getFragment(service, type);
        setNoAnimatedFragment(fragment);
    }

    private Fragment getFragment(final int service, final int type) {
        switch (service) {
            case CustomUser.VK:
                if (type == CustomUser.USER) return VkCustomUsersCreatorFragment.newInstance();
                else return VkCustomGroupCreatorFragment.newInstance();
            case CustomUser.INSTAGRAM:
                return InstCustomUsersCreatorFragment.newInstance();
            case CustomUser.TWITTER:
                return TwitterCustomUsersCreatorFragment.newInstance();
            case CustomUser.TUMBLR:
                return TumblrCustomUsersCreatorFragment.newInstance();
            case CustomUser.FLICKR:
                if (type == CustomUser.USER) return FlickrCustomUsersCreatorFragment.newInstance();
                else return FlickrCustomGroupsCreatorFragment.newInstance();
            default:
                return new Fragment();
        }
    }

    public static Intent newIntent(final Context context, final int service, final int type) {
        final Intent intent = new Intent(context, UserCreatorActivity.class);
        intent.putExtra(SERVICE, service);
        intent.putExtra(TYPE, type);
        return intent;
    }
}
