package com.kara4k.mediagrub.presenter.inst;


import com.google.gson.Gson;
import com.kara4k.mediagrub.api.InstApi;
import com.kara4k.mediagrub.model.SystemData;
import com.kara4k.mediagrub.model.database.MediaItem;
import com.kara4k.mediagrub.model.inst.SearchRequestObj;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.presenter.inst.mappers.SearchMapper;
import com.kara4k.mediagrub.view.adapters.recycler.AlbumItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InstPhotoSearchPresenter extends SearchPresenter {

    @Inject
    InstApi mInstApi;
    @Inject
    SearchMapper mSearchMapper;
    SystemData mSystemData;

    @Inject
    public InstPhotoSearchPresenter(SystemData systemData) {
        mSystemData = systemData;
        mUserItem = mSystemData.createSearchUser(UserItem.INSTAGRAM);
        mAlbumItem = mSystemData.createSearchAlbum();
    }

    @Override
    public void onQuerySubmit(String query) {
        String text = query.replaceAll(" ", StringUtils.EMPTY).toLowerCase();
        super.onQuerySubmit(text);

        String request = new SearchRequestObj(text, null).build();

        mInstApi.searchPhotos(request)
                .flatMap(mSearchMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSuccess(List<MediaItem> list) {
        if (mAlbumItem.getTotalSize() == AlbumItem.UNDEFINED) {
            mAlbumItem.setSize(String.valueOf(mSearchMapper.getAlbumSize()));
        }
        super.onSuccess(list);
    }

    @Override
    protected void loadMore(int offset, Consumer<List<MediaItem>> onPartLoaded) {
        SearchRequestObj searchRequestObj = new SearchRequestObj(mQuery, mSearchMapper.getLastId());
        String request = new Gson().toJson(searchRequestObj);

        mInstApi.searchPhotos(request)
                .flatMap(mSearchMapper)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onPartLoaded, this::onError);
    }
}
