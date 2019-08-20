package com.kara4k.mediagrub.presenter.base;


import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.CustomUserDao;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

abstract public class CustomUsersPresenter extends UsersListPresenter {

    private final DaoSession mDaoSession;
    private final CustomUserDao mCustomUserDao;

    protected abstract int getService();

    protected abstract int getUserType();

    public CustomUsersPresenter(final DaoSession daoSession) {
        mCustomUserDao = daoSession.getCustomUserDao();
        mDaoSession = daoSession;
    }

    @Override
    public void onStart() {
    }

    @Override
    protected boolean isActionModeEnabled() {
        return true;
    }

    @Override
    public void onItemClicked(final int position, final UserItem userItem) {
        getView().showAlbums(userItem);
    }

    @Override
    public void onSuccess(final List<UserItem> list) {
        if (list.isEmpty()) getView().showHint();
        else getView().hideHint();

        Collections.sort(list, (u1, u2) -> u1.getMainText().compareTo(u2.getMainText()));

        super.onSuccess(list);
    }

    @Override
    public void onDeleteSelectedConfirm() {
        Completable.fromAction(this::deleteSelectedUsers)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDeleteSuccess, this::onDeleteError);
    }

    private void deleteSelectedUsers() {
        final List<UserItem> selectedItems = mSelector.getSelectedItems();
        for (int i = 0; i < selectedItems.size(); i++) {
            final String key = getKey(selectedItems.get(i));
            final DeleteQuery<CustomUser> tableDeleteQuery = mDaoSession.queryBuilder(CustomUser.class)
                    .where(CustomUserDao.Properties.Key.eq(key),
                            CustomUserDao.Properties.Service.eq(getService()),
                            CustomUserDao.Properties.Type.eq(getUserType()))
                    .buildDelete();

            tableDeleteQuery.executeDeleteWithoutDetachingEntities();
        }
        mCustomUserDao.detachAll();
    }

    protected String getKey(final UserItem userItem) {
        return userItem.getId();
    }

    private void onDeleteSuccess() {
        getView().finishActionMode();
        onStart();
    }

    private void onDeleteError(final Throwable throwable) {
        getView().finishActionMode();
        onError(throwable);
    }

    protected List<CustomUser> getUsers() {
        return mCustomUserDao.queryBuilder()
                .where(CustomUserDao.Properties.Service.eq(getService()),
                        CustomUserDao.Properties.Type.eq(getUserType()))
                .build().list();
    }

    protected String getCustomIds() {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<CustomUser> users = getUsers();

        if (users.isEmpty()) return EMPTY;

        for (int i = 0; i < users.size(); i++) {
            final String key = users.get(i).getKey();
            stringBuilder.append(key);

            if (i != users.size() - 1) stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    protected CustomUserDao getCustomUserDao() {
        return mCustomUserDao;
    }

    public void onAddUser() {
        getView().showUserCreator();
    }
}
