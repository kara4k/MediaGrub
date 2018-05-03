package com.kara4k.mediagrub.view.base.hints;


import com.kara4k.mediagrub.R;

public abstract class HintLogInToSee extends Hint {

    private int mTemplateResId = R.string.hint_log_in_to_see;

    @Override
    abstract public String getMessage();

    @Override
    public int getIconRes() {
        return R.drawable.ic_account_circle_white_48dp;
    }

    public int getTemplateResId() {
        return mTemplateResId;
    }
}
