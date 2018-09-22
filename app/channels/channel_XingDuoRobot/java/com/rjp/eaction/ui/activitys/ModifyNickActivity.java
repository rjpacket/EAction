package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class ModifyNickActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, ModifyNickActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "修改昵称";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_nick;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
