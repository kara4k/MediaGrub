package com.kara4k.mediagrub.presenter.base;


import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.CustomUserDao;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.CustomCreatorIF;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class CustomCreatorPresenter extends Presenter implements MaybeObserver<UserItem> {

    public static final String URL_S = "https:/";

    @Inject
    CustomCreatorIF mView;
    private final CustomUserDao mCustomUserDao;
    protected UserItem mUserItem;

    public CustomCreatorPresenter(final DaoSession daoSession) {
        mCustomUserDao = daoSession.getCustomUserDao();
    }

    protected abstract String convertInput(String input);

    protected abstract void requestUserInfo(String id);

    protected abstract int getService();

    protected abstract int getUserType();

    public void onSearch(final String input) {
        mView.hideKeyboard();
        final String id = convertInput(input.trim().toLowerCase());
        requestUserInfo(id);
    }

    public void onAddClicked() {
        if (mUserItem == null) return;

        Single.fromCallable(this::getUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onExistUsersReceive, this::onError);
    }

    private void onExistUsersReceive(final List<CustomUser> customUsers) {
        if (customUsers.isEmpty()) {
            Completable.fromAction(this::addUserToDb)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onUserAdded, this::onError);
        } else {
            mView.showUserAlreadyExists();
        }
    }

    private void onUserAdded() {
        mView.hideUserInfo();

        if (mUserItem.isPrivate()){
            mView.showUserAddedButPrivate();
        } else {
            mView.showUserAdded();
        }
    }

    private void addUserToDb() {
        final CustomUser user = createUser();
        mCustomUserDao.insert(user);
    }

    private List<CustomUser> getUsers() {
        return mCustomUserDao.queryBuilder()
                .where(CustomUserDao.Properties.Service.eq(getService()),
                        CustomUserDao.Properties.Type.eq(getUserType()),
                        CustomUserDao.Properties.Key.eq(getDbKey()))
                .build().list();
    }

    private CustomUser createUser() {
        final CustomUser customUser = new CustomUser();
        customUser.setKey(getDbKey());
        customUser.setService(getService());
        customUser.setType(getUserType());
        return customUser;
    }

    protected String getDbKey() {
        return mUserItem.getId();
    }

    @Override
    public void onSubscribe(final Disposable d) {
    }

    @Override
    public void onSuccess(final UserItem userItem) {
        mUserItem = userItem;
        mView.showUserDetails(userItem);
    }

    @Override
    public void onError(final Throwable e) {
        onNotFound();
    }

    private void onNotFound() {
        mUserItem = null;
        mView.hideUserInfo();
        mView.showError(null);
    }

    @Override
    public void onComplete() {
        onNotFound();
    }

    public CustomCreatorIF getView() {
        return mView;
    }
}
