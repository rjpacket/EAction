package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class ReadTimeActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, ReadTimeActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "阅读时长";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read_time;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
