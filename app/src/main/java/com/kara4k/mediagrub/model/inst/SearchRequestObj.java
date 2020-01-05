package com.kara4k.mediagrub.model.inst;


import com.google.gson.Gson;

public class SearchRequestObj {

    public static final int LOAD_COUNT = 100;

    private String tag_name;
    private int first;
    private String after;

    public SearchRequestObj(String tag, String after) {
        this.tag_name = tag;
        this.first = LOAD_COUNT;
        this.after = after;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String build(){
        return new Gson().toJson(this);
    }
}
