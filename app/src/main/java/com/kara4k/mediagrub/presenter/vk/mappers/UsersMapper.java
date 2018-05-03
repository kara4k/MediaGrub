package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.users.Response;
import com.kara4k.mediagrub.model.vk.users.UsersResponse;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UsersMapper implements Function<UsersResponse, Observable<UserItem>> {

    @Inject
    public UsersMapper() {
    }

    @Override
    public Observable<UserItem> apply(UsersResponse usersResponse) throws Exception {
        return Observable.just(usersResponse)
                .filter(this::filterResponseError)
                .map(UsersResponse::getResponse)
                .flatMapIterable(responses -> responses)
                .map(this::map);
    }

    private boolean filterResponseError(UsersResponse response) throws Exception {
        if (response.getResponse() == null && response.getResponseError() != null) {
            String errorMsg = response.getResponseError().getErrorMsg();

            throw new Exception(errorMsg);
        }
        return true;
    }

    public UserItem map(Response response) throws Exception {
        UserItem userItem = new UserItem();
        String id = String.valueOf(response.getId());
        String mainText = String.format("%s %s", response.getFirstName(), response.getLastName());

        userItem.setId(id);
        userItem.setMainText(mainText);
        userItem.setAdditionText(id);
        userItem.setPhotoUrl(response.getPhoto100());
        userItem.setService(UserItem.VKONTAKTE);
        return userItem;
    }


}
