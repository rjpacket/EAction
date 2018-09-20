package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.UserLoginModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import butterknife.BindView;
import butterknife.OnClick;
import com.rjp.eaction.ui.app.App;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.utils.SPUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_save_password)
    TextView tvSavePassword;
    private String userName;
    private String userPassword;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {
        SPUtils spUtils = SPUtils.getInstance(mContext);
        String password = spUtils.getString(SPUtils.USER_PASSWORD);
        String username = spUtils.getString(SPUtils.USER_NAME);
        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(username)) {
            etUserName.setText(username);
            etPassword.setText(password);
            etPassword.setSelection(etPassword.length());
            tvSavePassword.setSelected(true);
        }
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

    @OnClick({R.id.tv_register, R.id.tv_login, R.id.tv_save_password, R.id.iv_wx_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                RegisterActivity.trendTo(mContext);
                break;
            case R.id.tv_login:
                userName = etUserName.getText().toString().trim();
                userPassword = etPassword.getText().toString().trim();
                login();
                break;
            case R.id.tv_save_password:
                tvSavePassword.setSelected(!tvSavePassword.isSelected());
                break;
            case R.id.iv_wx_login:
                wxLogin();
                break;
        }
    }

    public void wxLogin() {
        if (!App.mWxApi.isWXAppInstalled()) {
            Toast.makeText(mContext, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        App.mWxApi.sendReq(req);
    }

    private void login() {
        new NetUtils.Builder()
                .context(mContext)
                .url("user/login.jhtml")
                .param("userMobile", userName)
                .param("userPassword", AppUtils.md5(userPassword))
                .build()
                .model(new ResponseCallback<UserLoginModel>() {
                    @Override
                    public void success(UserLoginModel model) {
                        SPUtils.getInstance(mContext).save(SPUtils.USER_TOKEN, model.getToken());
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                        SPUtils.getInstance(mContext).save(SPUtils.USER_NAME, userName);
                        if (tvSavePassword.isSelected()) {
                            SPUtils.getInstance(mContext).save(SPUtils.USER_PASSWORD, userPassword);
                        } else {
                            SPUtils.getInstance(mContext).remove(SPUtils.USER_PASSWORD);
                        }
                        finish();
                    }

                    @Override
                    public void failure(String code, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
