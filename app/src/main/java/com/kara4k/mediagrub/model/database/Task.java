package com.kara4k.mediagrub.model.database;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity(nameInDb = "tasks")
public class Task {

    @Id
    private Long id;
    private String service;
    private String user;
    private String album;
    private String subPath;
    private String firstFile;
    private int type;
    private int notificationId;
    private int count;
    private int total;
    private boolean isRunning;
    private boolean isCompleted;
    @ToMany(referencedJoinProperty = "mTaskId")
    private List<MediaItem> mediaItems;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;
    @Generated(hash = 51599969)
    public Task(Long id, String service, String user, String album, String subPath,
            String firstFile, int type, int notificationId, int count, int total,
            boolean isRunning, boolean isCompleted) {
        this.id = id;
        this.service = service;
        this.user = user;
        this.album = album;
        this.subPath = subPath;
        this.firstFile = firstFile;
        this.type = type;
        this.notificationId = notificationId;
        this.count = count;
        this.total = total;
        this.isRunning = isRunning;
        this.isCompleted = isCompleted;
    }
    @Generated(hash = 733837707)
    public Task() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getService() {
        return this.service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public String getUser() {
        return this.user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getAlbum() {
        return this.album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getSubPath() {
        return this.subPath;
    }
    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }
    public String getFirstFile() {
        return this.firstFile;
    }
    public void setFirstFile(String firstFile) {
        this.firstFile = firstFile;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getNotificationId() {
        return this.notificationId;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getTotal() {
        return this.total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public boolean getIsRunning() {
        return this.isRunning;
    }
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public boolean getIsCompleted() {
        return this.isCompleted;
    }
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1819926603)
    public List<MediaItem> getMediaItems() {
        if (mediaItems == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MediaItemDao targetDao = daoSession.getMediaItemDao();
            List<MediaItem> mediaItemsNew = targetDao._queryTask_MediaItems(id);
            synchronized (this) {
                if (mediaItems == null) {
                    mediaItems = mediaItemsNew;
                }
            }
        }
        return mediaItems;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1576386787)
    public synchronized void resetMediaItems() {
        mediaItems = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }
   
}
