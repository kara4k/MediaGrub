package com.kara4k.mediagrub.presenter.vk.other;


import com.kara4k.mediagrub.api.VkApi;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

public class VkAuthManager {

    public static UserItem getLoggedUser(){
        if (VKSdk.isLoggedIn()){
            UserItem userItem = new UserItem();
            String userId = VKAccessToken.currentToken().userId;
            userItem.setId(userId);
            userItem.setService(UserItem.VKONTAKTE);
            userItem.setMainText("");
            userItem.setAdditionText("");
            userItem.setAdditionText(userId);
            return userItem;
        }
        return null;
    }

    public static String getToken() {
        String token;

        if (VKSdk.isLoggedIn()) token = VKAccessToken.currentToken().accessToken;
        else token = VkApi.SERVICE_KEY;

        return token;
    }
}
