package com.kara4k.mediagrub.presenter.vk.mappers;


import com.kara4k.mediagrub.model.vk.custom.group.CustomGroupResponse;
import com.kara4k.mediagrub.model.vk.custom.group.Response;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class CustomGroupMapper implements Function<CustomGroupResponse, Observable<UserItem>> {

    @Inject
    public CustomGroupMapper() {
    }

    @Override
    public Observable<UserItem> apply(final CustomGroupResponse response) throws Exception {
        return Observable.just(response)
                .filter(this::filterResponseError)
                .map(CustomGroupResponse::getResponse)
                .flatMapIterable(responses -> responses)
                .map(this::map);
    }

    private boolean filterResponseError(final CustomGroupResponse response) throws Exception {
        if (response.getResponse() == null && response.getResponseError() != null) {
            final String errorMsg = response.getResponseError().getErrorMsg();

            throw new Exception(errorMsg);
        }
        return true;
    }

    public UserItem map(final Response response) throws Exception {
        final UserItem userItem = new UserItem();
        final String id = String.valueOf(response.getId());
        final String mainText = response.getName();

        userItem.setId(id);
        userItem.setMainText(mainText);
        userItem.setAdditionText(response.getScreenName());
        userItem.setPhotoUrl(response.getPhoto100());
        userItem.setService(UserItem.VKONTAKTE);
        userItem.setPrivate(response.getIsClosed() != null && !response.getIsClosed().equals(0L));

        return userItem;
    }
}
