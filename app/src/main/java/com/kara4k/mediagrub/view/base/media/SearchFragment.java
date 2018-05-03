package com.kara4k.mediagrub.view.base.media;


import android.view.Menu;
import android.view.MenuItem;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.MediaListPresenter;
import com.kara4k.mediagrub.presenter.base.SearchPresenter;
import com.kara4k.mediagrub.view.base.hints.Hint;
import com.kara4k.mediagrub.view.base.hints.HintSearch;

public abstract class SearchFragment extends MediaListFragment implements SearchViewIF {

    protected abstract SearchPresenter getSearchPresenter();

    @Override
    protected MediaListPresenter getMediaListPresenter() {
        return getSearchPresenter();
    }

    @Override
    protected void onMenuInflated(Menu menu) {
        super.onMenuInflated(menu);
        menu.findItem(R.id.menu_item_search)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mSearchView.setIconified(false);
        mSearchView.clearFocus();
    }

    @Override
    protected Hint getHint() {
        return new HintSearch() {
            @Override
            public String getMessage() {
                return getString(R.string.hint_search);
            }
        };
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getSearchPresenter().onQuerySubmit(query);
        mSearchView.clearFocus();
        return true;
    }

    @Override
    public void showNothingFound() {
        showToast(getString(R.string.message_nothing_found));
    }
}
