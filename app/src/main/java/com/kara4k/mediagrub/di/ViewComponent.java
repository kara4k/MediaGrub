package com.kara4k.mediagrub.di;

import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.di.scopes.PerActivity;
import com.kara4k.mediagrub.view.flickr.FlickrAlbumsListFragment;
import com.kara4k.mediagrub.view.flickr.FlickrGroupsPhotoListFragment;
import com.kara4k.mediagrub.view.flickr.FlickrUserPhotoListFragment;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomGroupsCreatorFragment;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomGroupsListFragment;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.flickr.custom.FlickrCustomUsersListFragment;
import com.kara4k.mediagrub.view.flickr.search.FlickrPhotoSearchFragment;
import com.kara4k.mediagrub.view.inst.InstPhotoListFragment;
import com.kara4k.mediagrub.view.inst.custom.InstCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.inst.custom.InstCustomUsersListFragment;
import com.kara4k.mediagrub.view.inst.search.InstPhotoSearchFragment;
import com.kara4k.mediagrub.view.inst.search.InstUserSearchFragment;
import com.kara4k.mediagrub.view.main.MainActivity;
import com.kara4k.mediagrub.view.main.downloads.ActiveTasksFragment;
import com.kara4k.mediagrub.view.main.downloads.CompletedTasksFragment;
import com.kara4k.mediagrub.view.main.media.MediaPageFragment;
import com.kara4k.mediagrub.view.tumblr.TumblrPhotoListFragment;
import com.kara4k.mediagrub.view.tumblr.custom.TumblrCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.tumblr.custom.TumblrCustomUsersListFragment;
import com.kara4k.mediagrub.view.twitter.TwitterPhotoListFragment;
import com.kara4k.mediagrub.view.twitter.TwitterUserSearchFragment;
import com.kara4k.mediagrub.view.twitter.TwitterVideoListFragment;
import com.kara4k.mediagrub.view.twitter.custom.TwitterCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.twitter.custom.TwitterCustomUsersListFragment;
import com.kara4k.mediagrub.view.vk.VkPhotoAlbumsFragment;
import com.kara4k.mediagrub.view.vk.VkPhotoListFragment;
import com.kara4k.mediagrub.view.vk.auth.VkAuthFragment;
import com.kara4k.mediagrub.view.vk.custom.VkCustomGroupCreatorFragment;
import com.kara4k.mediagrub.view.vk.custom.VkCustomGroupsListFragment;
import com.kara4k.mediagrub.view.vk.custom.VkCustomUsersCreatorFragment;
import com.kara4k.mediagrub.view.vk.custom.VkCustomUsersListFragment;
import com.kara4k.mediagrub.view.vk.friends.FriendsFragment;
import com.kara4k.mediagrub.view.vk.friends.GroupsFragment;
import com.kara4k.mediagrub.view.vk.search.VkSearchFragment;

import dagger.Component;

@Component(modules = ViewModule.class, dependencies = AppComponent.class)
@PerActivity
public interface ViewComponent {

    void injectMainActivity(MainActivity activity);

    void injectActiveTasksFragment(ActiveTasksFragment fragment);

    void injectCompletedTasksFragment(CompletedTasksFragment fragment);

    void injectMediaPageFragment(MediaPageFragment fragment);

    void injectVkFriendsFragment(FriendsFragment fragment);

    void injectVkGroupsFragment(GroupsFragment fragment);

    void injectVkPhotoAlbumsFrag(VkPhotoAlbumsFragment fragment);

    void injectVkPhotoListFrag(VkPhotoListFragment fragment);

    void injectVkSearchFrag(VkSearchFragment fragment);

    void injectVkCustomUsersFrag(VkCustomUsersListFragment fragment);

    void injectVkCustomGroupsFrag(VkCustomGroupsListFragment fragment);

    void injectVkAuthFragment(VkAuthFragment fragment);

    void injectVkCustomUsersCreatorFrag(VkCustomUsersCreatorFragment fragment);

    void injectVkCustomGroupCreatorFrag(VkCustomGroupCreatorFragment fragment);

    void injectInstCustomUsers(InstCustomUsersListFragment fragment);

    void injectInstCustomUsersCreator(InstCustomUsersCreatorFragment fragment);

    void injectInstUserSearchFragment(InstUserSearchFragment fragment);

    void injectInstPhotoListFragment(InstPhotoListFragment fragment);

    void injectInstPhotoSearchFrag(InstPhotoSearchFragment fragment);

    void injectTwitterCustomUsersFragment(TwitterCustomUsersListFragment fragment);

    void injectTwitterCustomUsersCreatorFragment(TwitterCustomUsersCreatorFragment fragment);

    void injectTwitterPhotoListFrag(TwitterPhotoListFragment fragment);

    void injectTwitterVideoListFrag(TwitterVideoListFragment fragment);

    void injectTwitterUserSearchFragment(TwitterUserSearchFragment fragment);

    void injectTumblrCustomUsersListFrag(TumblrCustomUsersListFragment fragment);

    void injectTumblrCustomUsersCreatorFrag(TumblrCustomUsersCreatorFragment fragment);

    void injectTumblrPhotoListFrag(TumblrPhotoListFragment fragment);

    void injectFlickrCustomUsersListFrag(FlickrCustomUsersListFragment fragment);

    void injectFlickrCustomUsersCreatorFrag(FlickrCustomUsersCreatorFragment fragment);

    void injectFlickrCustomGroupsListFrag(FlickrCustomGroupsListFragment fragment);

    void injectFlickrCustomGroupsCreatorFrag(FlickrCustomGroupsCreatorFragment fragment);

    void injectFlickrAlbumsListFragment (FlickrAlbumsListFragment fragment);

    void injectFlickrUserPhotosFragment (FlickrUserPhotoListFragment fragment);

    void injectFlickrGroupPhotosFragment (FlickrGroupsPhotoListFragment fragment);

    void injectFlickrPhotoSearchFragment(FlickrPhotoSearchFragment fragment);

}
