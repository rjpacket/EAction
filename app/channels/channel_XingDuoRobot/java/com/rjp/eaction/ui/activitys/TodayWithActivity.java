package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class TodayWithActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, TodayWithActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "今日陪伴";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_today_with;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
