package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class FamilyWithActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, FamilyWithActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "亲子陪伴";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_with;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
