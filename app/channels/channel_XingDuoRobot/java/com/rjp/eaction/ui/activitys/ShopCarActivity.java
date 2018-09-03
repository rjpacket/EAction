package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class ShopCarActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, ShopCarActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "购物车";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
