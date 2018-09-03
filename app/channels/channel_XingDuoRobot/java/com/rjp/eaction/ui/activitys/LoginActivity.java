package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

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
        return R.layout.activity_login;
    }

    @Override
    protected String getPageTitle() {
        return "登录";
    }

    @OnClick({R.id.tv_register, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                break;
            case R.id.tv_login:
                login();
                break;
        }
    }

    private void login() {
        new NetUtils.Builder()
                .context(mContext)
                .url("user/login.jhtml")
                .param("username", "admin")
                .param("password", "admin")
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {

                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }
}
