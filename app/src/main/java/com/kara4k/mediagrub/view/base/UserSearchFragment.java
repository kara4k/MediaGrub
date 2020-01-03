package com.kara4k.mediagrub.view.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.presenter.base.UserSearchPresenter;
import com.kara4k.mediagrub.view.adapters.recycler.UserItem;
import com.kara4k.mediagrub.view.adapters.recycler.UserSearchAdapter;
import com.kara4k.mediagrub.view.base.hints.Hint;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class UserSearchFragment<P extends UserSearchPresenter> extends BaseFragment implements UserSearchIF {

    @BindView(R.id.input_edit_text)
    EditText mEditText;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.users_layout)
    RelativeLayout mUsersLayout;

    @Inject
    P mPresenter;
    private UserSearchAdapter mAdapter;

    protected Hint getHint() {
        return new Hint() {
            @Override
            public int getIconRes() {
                return R.drawable.ic_group_add_white_48dp;
            }

            @Override
            public String getMessage() {
                return getString(R.string.hint_search_users);
            }
        };
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        mRecyclerView.setAdapter(mAdapter = new UserSearchAdapter(mPresenter));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setupHint();
        showHint();
    }

    private void setupHint() {
        final Hint hint = getHint();

        if (hint != null) {
            final ImageView mHintImageView = mUsersLayout.findViewById(R.id.hint_image);
            final TextView mHintTextView = mUsersLayout.findViewById(R.id.hint_message);
            final int iconRes = hint.getIconRes();

            if (iconRes != UNDEFINED) {
                mHintImageView.setImageResource(iconRes);
            }

            mHintTextView.setText(hint.getMessage());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_search_users;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.empty_menu;
    }

    @Override
    public void showError(final String message) {
        showToast(message);
    }

    @OnClick(R.id.search_btn)
    public void onSearchBtnClick() {
        if (mRecyclerView.getVisibility() == View.VISIBLE){
            mRecyclerView.setVisibility(View.GONE);
        }

        mPresenter.onSearchClicked(mEditText.getText().toString());
    }

    @Override
    public void showFoundUsers(final List<UserItem> userItems) {
        if (mRecyclerView.getVisibility() == View.GONE){
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        hideHint();

        mAdapter.setUserItems(userItems);
    }

    @Override
    public void showUserAdded() {
        showToast(getString(R.string.message_success_added));
    }

    @Override
    public void showUserAlreadyExists() {
        showToast(getString(R.string.message_already_exists));
    }

    @Override
    public void hideKeyboard() {
        hideSoftKeyboard();
    }

    @Override
    public void showUserAddedButPrivate() {
        showToast(getString(R.string.message_success_added_but_private));
    }

    @Override
    public void showProgress() {
        mUsersLayout.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mUsersLayout.findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }

    public void showHint() {
        mUsersLayout.findViewById(R.id.hint_layout).setVisibility(View.VISIBLE);
    }

    public void hideHint() {
        mUsersLayout.findViewById(R.id.hint_layout).setVisibility(View.GONE);
    }
}
