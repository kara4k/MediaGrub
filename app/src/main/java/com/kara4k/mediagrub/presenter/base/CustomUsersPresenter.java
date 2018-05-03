package com.kara4k.mediagrub.presenter.base;


import com.kara4k.mediagrub.model.database.CustomUser;
import com.kara4k.mediagrub.model.database.CustomUserDao;
import com.kara4k.mediagrub.model.database.DaoSession;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

abstract public class CustomUsersPresenter extends UsersListPresenter {

    private DaoSession mDaoSession;
    private CustomUserDao mCustomUserDao;

    protected abstract int getService();

    protected abstract int getUserType();

    public CustomUsersPresenter(DaoSession daoSession) {
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
    public void onItemClicked(int position, UserItem userItem) {
        getView().showAlbums(userItem);
    }

    @Override
    public void onSuccess(List<UserItem> list) {
        if (list.isEmpty()) getView().showHint();
        else getView().hideHint();
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
        List<UserItem> selectedItems = mSelector.getSelectedItems();
        for (int i = 0; i < selectedItems.size(); i++) {
            String key = getKey(selectedItems.get(i));
            DeleteQuery<CustomUser> tableDeleteQuery = mDaoSession.queryBuilder(CustomUser.class)
                    .where(CustomUserDao.Properties.Key.eq(key),
                            CustomUserDao.Properties.Service.eq(getService()),
                            CustomUserDao.Properties.Type.eq(getUserType()))
                    .buildDelete();

            tableDeleteQuery.executeDeleteWithoutDetachingEntities();
        }
        mCustomUserDao.detachAll();
    }

    protected String getKey(UserItem userItem) {
        return userItem.getId();
    }

    private void onDeleteSuccess() {
        getView().finishActionMode();
        onStart();
    }

    private void onDeleteError(Throwable throwable) {
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
        StringBuilder stringBuilder = new StringBuilder();
        List<CustomUser> users = getUsers();

        if (users.isEmpty()) return EMPTY;

        for (int i = 0; i < users.size(); i++) {
            String key = users.get(i).getKey();
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
