package com.kara4k.mediagrub.model.inst;


import com.google.gson.Gson;

public class RequestObject {

    public static final int LOAD_COUNT = 100;

    private String id;
    private Integer first;
    private String after;
    private String shortcode;

    public RequestObject(String id, String after) {
        this.id = id;
        this.first = LOAD_COUNT;
        this.after = after;
    }

    public RequestObject(String shortcode) {
        this.shortcode = shortcode;
    }

    public String create(){
        return new Gson().toJson(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
