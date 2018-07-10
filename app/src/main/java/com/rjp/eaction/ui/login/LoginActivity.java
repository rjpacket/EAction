package com.rjp.eaction.ui.login;

import android.content.Context;
import android.content.Intent;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;

public class LoginActivity extends BaseActivity {

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void handle() {

    }
}
