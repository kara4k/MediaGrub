package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.users.Response;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

public class CustomGroupMapper extends UsersMapper {

    @Inject
    public CustomGroupMapper() {
    }

    @Override
    public UserItem map(Response response) throws Exception {
        UserItem userItem = new UserItem();
        String id = String.valueOf(response.getId());
        String mainText = response.getName();

        userItem.setId(id);
        userItem.setMainText(mainText);
        userItem.setAdditionText(response.getScreenName());
        userItem.setPhotoUrl(response.getPhoto100());
        userItem.setService(UserItem.VKONTAKTE);
        return userItem;
    }
}
