package com.rjp.eaction.ui.activitys;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;

public class BindMobileActivity extends BaseActivity {

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
        return R.layout.activity_bind_mobile;
    }

    @Override
    protected String getPageTitle() {
        return "绑定手机号";
    }

}
