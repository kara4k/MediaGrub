package com.kara4k.mediagrub.model.database;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "custom_users")
public class CustomUser {

    public static final int VK = 1;
    public static final int INSTAGRAM = 2;
    public static final int TWITTER = 3;
    public static final int TUMBLR = 4;
    public static final int FLICKR = 5;

    public static final int USER = 1;
    public static final int GROUP = 2;

    @Id
    private Long id;
    private String key;
    private int service;
    private int type;

    @Generated(hash = 765767096)
    public CustomUser(Long id, String key, int service, int type) {
        this.id = id;
        this.key = key;
        this.service = service;
        this.type = type;
    }

    @Generated(hash = 1024669365)
    public CustomUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }
}
