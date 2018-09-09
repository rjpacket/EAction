package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.UserLoginModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import butterknife.BindView;
import butterknife.OnClick;
import com.rjp.eaction.utils.SPUtils;

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
                .param("userName", "admin")
                .param("userPassword", "admin")
                .build()
                .model(new ResponseCallback<UserLoginModel>() {
                    @Override
                    public void success(UserLoginModel model) {
                        SPUtils.getInstance(mContext).save(SPUtils.USER_TOKEN, model.getToken());
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void failure(String code, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
