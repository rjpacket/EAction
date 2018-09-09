package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class ModifyPasswordActivity extends BaseActivity {

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, ModifyPasswordActivity.class));
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected String getPageTitle() {
        return "修改密码";
    }

}
