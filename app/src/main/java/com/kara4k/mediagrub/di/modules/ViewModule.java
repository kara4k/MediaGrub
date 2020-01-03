package com.kara4k.mediagrub.di.modules;


import com.kara4k.mediagrub.di.scopes.PerActivity;
import com.kara4k.mediagrub.view.base.AuthViewIF;
import com.kara4k.mediagrub.view.base.CustomCreatorIF;
import com.kara4k.mediagrub.view.base.UserSearchIF;
import com.kara4k.mediagrub.view.base.UsersViewIF;
import com.kara4k.mediagrub.view.base.ViewIF;
import com.kara4k.mediagrub.view.base.media.AlbumViewIF;
import com.kara4k.mediagrub.view.base.media.MediaListViewIF;
import com.kara4k.mediagrub.view.base.media.SearchViewIF;
import com.kara4k.mediagrub.view.main.MainViewIF;
import com.kara4k.mediagrub.view.main.downloads.ActiveTasksViewIF;
import com.kara4k.mediagrub.view.main.downloads.CompletedTasksViewIF;
import com.kara4k.mediagrub.view.main.media.MediaPageViewIF;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private ViewIF mViewIF;

    public ViewModule(ViewIF viewIF) {
        mViewIF = viewIF;
    }

    @Provides
    @PerActivity
    MainViewIF provideMainViewIF(){
        return (MainViewIF) mViewIF;
    }

    @Provides
    @PerActivity
    AuthViewIF provideAuthViewIF(){
        return (AuthViewIF) mViewIF;
    }

    @Provides
    @PerActivity
    ActiveTasksViewIF provideActiveTasksViewIF(){
        return (ActiveTasksViewIF) mViewIF;
    }

    @Provides
    @PerActivity
    CompletedTasksViewIF provideCompletedTasksViewIF(){
        return (CompletedTasksViewIF) mViewIF;
    }

    @Provides
    @PerActivity
    UsersViewIF provideUsersViewIF(){
        return (UsersViewIF) mViewIF;
    }

    @Provides
    @PerActivity
    AlbumViewIF provideAlbumViewIF() {return (AlbumViewIF) mViewIF;}

    @Provides
    @PerActivity
    MediaPageViewIF provideMediaPageViewIF(){
        return (MediaPageViewIF) mViewIF;
    }

    @Provides
    @PerActivity
    MediaListViewIF provideMediaListViewIF() {return (MediaListViewIF) mViewIF;}

    @Provides
    @PerActivity
    SearchViewIF provideSearchViewIF() {return (SearchViewIF) mViewIF;}

    @Provides
    @PerActivity
    CustomCreatorIF provideCustomCreatorIF() {return (CustomCreatorIF) mViewIF;}

    @Provides
    @PerActivity
    UserSearchIF provideUserSearchIF() {return (UserSearchIF) mViewIF;}
}
