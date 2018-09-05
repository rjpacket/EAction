package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import butterknife.BindView;
import butterknife.OnClick;

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
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {

                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });

//        String url = "http://118.89.217.77:8090/edu/user/login.jhtml";

//        OkHttpUtils
//                .post()
//                .url(url)
//                .addParams("userName", "admin")
//                .addParams("userPassword", "admin")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        LogUtils.e("--------->", e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        LogUtils.e("--------->", response);
//                    }
//                });
    }
}
