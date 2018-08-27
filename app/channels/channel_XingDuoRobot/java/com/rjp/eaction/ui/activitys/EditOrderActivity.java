package com.rjp.eaction.ui.activitys;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class EditOrderActivity extends BaseActivity {

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_order;
    }

    @Override
    protected String getPageTitle() {
        return "填写订单";
    }

}
