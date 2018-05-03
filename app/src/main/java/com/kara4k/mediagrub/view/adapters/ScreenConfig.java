package com.kara4k.mediagrub.view.adapters;


import android.graphics.Point;

public class ScreenConfig {

    private Point mScreenSize;
    private int mOrientation;

    public ScreenConfig(Point screenSize, int orientation) {
        mScreenSize = screenSize;
        mOrientation = orientation;
    }

    public Point getScreenSize() {
        return mScreenSize;
    }

    public void setScreenSize(Point screenSize) {
        mScreenSize = screenSize;
    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
    }
}
