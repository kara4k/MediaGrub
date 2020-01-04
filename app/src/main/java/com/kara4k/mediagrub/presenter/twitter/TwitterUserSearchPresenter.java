package com.kara4k.mediagrub.presenter.twitter;

import com.kara4k.mediagrub.api.TwitterApi;
import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.model.twitter.users.search.UserSearchResponse;
import com.kara4k.mediagrub.presenter.base.UserSearchPresenter;
import com.kara4k.mediagrub.presenter.twitter.mappers.UsersMapper;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.UserSearchIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class TwitterUserSearchPresenter extends UserSearchPresenter {

    @Inject
    TwitterApi mTwitterApi;
    @Inject
    UsersMapper mUsersMapper;

    @Inject
    public TwitterUserSearchPresenter(final UserSearchIF view, final DaoSession daoSession) {
        super(view, daoSession);
    }

    @Override
    protected String getDbKey(final UserItem userItem) {
        return userItem.getAdditionText();
    }

    @Override
    protected void performUserSearch(final String text) {
        mTwitterApi.searchUsers(TwitterApi.BASE_USER_SEARCH_URL.concat(text))
                .map(UserSearchResponse::getPage)
                .map(mUsersMapper.mapSearchedUsers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected int getService() {
        return CustomUser.TWITTER;
    }

    @Override
    protected int getUserType() {
        return CustomUser.USER;
    }
}
