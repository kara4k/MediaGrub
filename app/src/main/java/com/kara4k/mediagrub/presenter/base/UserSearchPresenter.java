package com.kara4k.mediagrub.presenter.base;

import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.CustomUserDao;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.base.UserSearchIF;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class UserSearchPresenter extends Presenter implements SingleObserver<List<UserItem>> {

    protected UserSearchIF mView;
    private final CustomUserDao mCustomUserDao;

    public UserSearchPresenter(final UserSearchIF view, final DaoSession daoSession) {
        this.mView = view;
        this.mCustomUserDao = daoSession.getCustomUserDao();
    }

    protected abstract String getDbKey(UserItem userItem);

    protected abstract void performUserSearch(String text);

    protected abstract int getService();

    protected abstract int getUserType();

    public void onSearchClicked(final String text) {
        mView.hideKeyboard();
        mView.showProgress();
        performUserSearch(text);
    }

    @Override
    public void onSubscribe(final Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onSuccess(final List<UserItem> userItems) {
        mView.hideProgress();
        mView.showFoundUsers(userItems);
    }

    @Override
    public void onError(final Throwable e) {
        mView.showError(e.getMessage());
        mView.hideProgress();
    }

    // TODO: 1/3/20 merge with custom user creator presenter?
    public void addUser(final UserItem userItem) {
        Single.fromCallable(() -> getUsers(userItem))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> onExistUsersReceive(users, userItem), this::onError);
    }

    private List<CustomUser> getUsers(final UserItem userItem) {
        final String dbKey = getDbKey(userItem);

        return mCustomUserDao.queryBuilder()
                .where(CustomUserDao.Properties.Service.eq(getService()),
                        CustomUserDao.Properties.Type.eq(getUserType()),
                        CustomUserDao.Properties.Key.eq(dbKey))
                .build().list();
    }

    private void onExistUsersReceive(final List<CustomUser> customUsers, final UserItem userItem) {
        if (customUsers.isEmpty()) {
            final String dbKey = getDbKey(userItem);

            Completable.fromAction(() -> addUserToDb(dbKey))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> onUserAdded(userItem), this::onError);
        } else {
            mView.showUserAlreadyExists();
        }
    }

    private void onUserAdded(UserItem userItem) {
        if (userItem.isPrivate()){
            mView.showUserAddedButPrivate();
        } else {
            mView.showUserAdded();
        }
    }

    private void addUserToDb(final String dbKey) {
        final CustomUser user = createUser(dbKey);
        mCustomUserDao.insert(user);
    }

    private CustomUser createUser(final String dbKey) {
        final CustomUser customUser = new CustomUser();
        customUser.setKey(dbKey);
        customUser.setService(getService());
        customUser.setType(getUserType());
        return customUser;
    }
}
