package com.kara4k.mediagrub.view.base;


import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.CustomUsersPresenter;

public abstract class CustomUsersListFragment<P extends CustomUsersPresenter>
        extends UsersListFragment<P> {

    public static final int MENU_USER_ADD_ID = 115;

    protected  boolean isHasUserSearchMenuItem(){
        return false;
    }

    @Override
    protected void onMenuInflated(final Menu menu) {
        super.onMenuInflated(menu);
        if (menu.findItem(MENU_USER_ADD_ID) != null) return;

        final MenuItem menuItem = menu.add(Menu.NONE, MENU_USER_ADD_ID,
                2, R.string.menu_item_add);
        menuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(R.drawable.ic_person_add_white_24dp);

        if (isHasUserSearchMenuItem()){
            menu.findItem(R.id.menu_item_search_users).setVisible(true);
        }
    }

    @Override
    public void startActionMode() {
        setModeCallback(new CustomUsersMode());
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case MENU_USER_ADD_ID:
                getPresenter().onAddUser();
                return true;
            case R.id.menu_item_search_users:
                getPresenter().onSearchUsers();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class CustomUsersMode extends ModeCallback {

        @Override
        protected int getModeMenu() {
            return R.menu.fragment_action_completed_tasks;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode mode, final MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_item_action_select_all:
                    getPresenter().selectAll();
                    break;
                case R.id.menu_item_action_delete:
                    getPresenter().onDeleteSelected();
                    break;
            }
            return true;
        }
    }
}
