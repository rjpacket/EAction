package com.rjp.eaction.base;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;

public class TemplateActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, TemplateActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "购物车";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
