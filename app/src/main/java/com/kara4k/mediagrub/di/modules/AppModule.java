package com.kara4k.mediagrub.di.modules;

import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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

    public static final String NOTIFICATION_CHANNEL_ID = "mediagrubNotificationChannelId";

    Context mContext;

    public AppModule(final Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(final Context context){
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    DownloadManager provideDownloadManager(final Context context){
        return (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManagerCompat provideNotifManager(final Context context){
        if (Build.VERSION.SDK_INT >= 26){
            final NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            final NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "notification_channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        return NotificationManagerCompat.from(context);
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(final Context context){
        final DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "database");
        final Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }
}
