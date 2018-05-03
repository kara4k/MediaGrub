package com.kara4k.mediagrub.view.vk.friends;


import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.di.DaggerViewComponent;
import com.kara4k.mediagrub.di.modules.ViewModule;
import com.kara4k.mediagrub.presenter.vk.GroupsPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.UsersListFragment;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintLogInToSee;
import com.kara4k.mediagrub.view.vk.VkPhotoAlbumsFragment;

public class GroupsFragment extends UsersListFragment<GroupsPresenter> {

    public static GroupsFragment newInstance() {
        return new GroupsFragment();
    }

    @Override
    protected void injectDaggerDependencies() {
        DaggerViewComponent.builder()
                .appComponent(getAppComponent())
                .viewModule(new ViewModule(this))
                .build().injectVkGroupsFragment(this);
    }

    @Override
    protected Hint getHint() {
        return new HintLogInToSee() {
            @Override
            public String getMessage() {
                return getString(getTemplateResId(), getString(R.string.hint_groups));
            }
        };
    }

    @Override
    public void showAlbums(UserItem userItem) {
        addFragment(VkPhotoAlbumsFragment.newInstance(userItem));
    }
}
