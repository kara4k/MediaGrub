package com.kara4k.mediagrub.other;


import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kara4k.mediagrub.R;

public class AdManager {

    private static final long AD_INTERVAL = 1000 * 60 * 2;

    private static AdManager sAdManager;

    private InterstitialAd mInterstitialAd;
    private long mLastOpen = System.currentTimeMillis();
    private boolean mIsShow = true;

    public static AdManager getInstance(Context context) {
        if (sAdManager == null) {
            sAdManager = new AdManager(context);
        }
        return sAdManager;
    }

    private AdManager(Context context) {
        MobileAds.initialize(context, context.getString(R.string.ad_app_id));
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.ad_interst_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mIsShow) {
                    mInterstitialAd.show();
                    mIsShow = false;
                }
            }

            @Override
            public void onAdOpened() {
                mLastOpen = System.currentTimeMillis();
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public void checkShowAd() {
        long currentTimeMillis = System.currentTimeMillis();

        if (currentTimeMillis - mLastOpen > AD_INTERVAL && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
