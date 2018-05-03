package com.kara4k.mediagrub.view.base.hints;


import com.kara4k.mediagrub.R;

public abstract class HintCustom extends Hint {

    private int mTemplateResId = R.string.hint_custom_users;

    @Override
    public int getIconRes() {
        return R.drawable.ic_person_add_white_48dp;
    }

    public int getTemplateResId() {
        return mTemplateResId;
    }
}
