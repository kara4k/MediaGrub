package com.kara4k.mediagrub.view.vk.own;


import android.os.Bundle;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintLogInToSee;
import com.kara4k.mediagrub.view.vk.VkPhotoAlbumsFragment;

public class VkOwnAlbumsFragment extends VkPhotoAlbumsFragment {

    public static VkOwnAlbumsFragment newInstance(UserItem userItem) {
        Bundle args = new Bundle();
        args.putSerializable(USER, userItem);
        VkOwnAlbumsFragment fragment = new VkOwnAlbumsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Hint getHint() {
       return new HintLogInToSee() {
           @Override
           public String getMessage() {
               return getString(getTemplateResId(), getString(R.string.hint_albums));
           }
       };
    }
}
