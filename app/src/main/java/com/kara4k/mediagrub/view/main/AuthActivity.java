package com.kara4k.mediagrub.view.main;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.base.BaseActivity;
import com.kara4k.mediagrub.view.vk.auth.VkAuthFragment;

public class AuthActivity extends BaseActivity {

    public static final String SERVICE = "service";
    public static final int VK = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_container;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        int service = getIntent().getIntExtra(SERVICE, EMPTY);

        Fragment authFragment = getAuthFragment(service);
        setNoAnimatedFragment(authFragment);
    }

    private Fragment getAuthFragment(int service) {
        switch (service) {
            case VK:
                return VkAuthFragment.newInstance();
            default:
                return new Fragment();
        }
    }

    public static Intent newIntent(Context context, int service) {
        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra(SERVICE, service);
        return intent;
    }
}
