package com.kara4k.mediagrub.di.modules;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationManagerCompat;

import com.kara4k.mediagrub.model.database.DaoMaster;
import com.kara4k.mediagrub.model.database.DaoSession;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.DOWNLOAD_SERVICE;

@Module
public class AppModule {

    Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Context context){
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    DownloadManager provideDownloadManager(Context context){
        return (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManagerCompat provideNotifManager(Context context){
        return NotificationManagerCompat.from(context);
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "database");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

}
