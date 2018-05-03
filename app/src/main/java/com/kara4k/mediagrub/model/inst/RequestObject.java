package com.kara4k.mediagrub.model.inst;


public class RequestObject {

    public static final int LOAD_COUNT = 100;

    private String id;
    private int first;
    private String after;

    public RequestObject(String id, String after) {
        this.id = id;
        this.first = LOAD_COUNT;
        this.after = after;
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
}
