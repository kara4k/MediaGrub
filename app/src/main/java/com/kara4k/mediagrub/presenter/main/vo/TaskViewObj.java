package com.kara4k.mediagrub.presenter.main.vo;


import com.kara4k.mediagrub.view.adapters.recycler.SelectableItem;

public class TaskViewObj extends SelectableItem{

    private Long mId;
    private String mTitle;
    private String mService;
    private String mSummary;
    private String mSubPath;
    private String mFirstFile;
    private int mType;
    private int count;
    private int total;
    private boolean isRunning;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getService() {
        return mService;
    }

    public void setService(String service) {
        mService = service;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSubPath() {
        return mSubPath;
    }

    public void setSubPath(String subPath) {
        mSubPath = subPath;
    }

    public String getFirstFile() {
        return mFirstFile;
    }

    public void setFirstFile(String firstFile) {
        mFirstFile = firstFile;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
