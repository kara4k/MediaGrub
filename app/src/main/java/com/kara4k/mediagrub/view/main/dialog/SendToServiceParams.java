package com.kara4k.mediagrub.view.main.dialog;

import android.os.Bundle;

public class SendToServiceParams {

    public static final String SERVICE_BUNDLE_TEXT = "sendToServiceBundleText";

    private int service;
    private int type;
    private Bundle bundle;

    public void createBundle(final String text) {
        final Bundle bundle = new Bundle();
        bundle.putString(SERVICE_BUNDLE_TEXT, text);

        this.bundle = bundle;
    }

    public int getService() {
        return service;
    }

    public void setService(final int service) {
        this.service = service;
    }

    public int getType() {
        return type;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(final Bundle bundle) {
        this.bundle = bundle;
    }
}
