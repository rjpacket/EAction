package com.rjp.eaction.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.baseAF.Const;
import com.rjp.eaction.bean.LoginModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.SPUtils;
import com.rjp.eaction.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone) EditText etPhone;
    @BindView(R.id.tv_get_code) TextView tvGetCode;
    @BindView(R.id.et_code) EditText etCode;
    @BindView(R.id.tv_login) TextView tvLogin;
    private CountDownTimer getCodeTimer;

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
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

    @Override
    protected void handle() {
        //获取保存的倒计时
        long loginEndtime = SPUtils.getInstance().getLong("login_endtime");
        if(loginEndtime > System.currentTimeMillis()){
            long timeCount = (loginEndtime - System.currentTimeMillis()) / 1000;
            updateTimer(timeCount);
            tvGetCode.setEnabled(false);
            getCodeTimer.start();
        }
    }

    /**
     * 更新计时器的时间
     * @param timeCount
     */
    private void updateTimer(long timeCount) {
        getCodeTimer = new CountDownTimer(timeCount * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetCode.setText("重新发送(" + (millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                tvGetCode.setText("获取验证码");
                tvGetCode.setEnabled(true);
            }
        };
    }

    @OnClick({R.id.tv_get_code, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                String phone = etPhone.getText().toString().trim();
                if(!TextUtils.isEmpty(phone)){
                    getPhoneCode(phone);
                }else{
                    ToastUtils.showToast(mContext, "请先输入手机号");
                }
                break;
            case R.id.tv_login:
                String phoneNumber = etPhone.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                if(!TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(code)){
                    loginByCode(phoneNumber, code);
                }else{
                    ToastUtils.showToast(mContext, "手机号或者验证码无效");
                }
                break;
        }
    }

    /**
     * 验证码登录
     * @param phoneNumber
     * @param code
     */
    private void loginByCode(String phoneNumber, String code) {
        new NetUtils.Builder()
                .context(mContext)
                .url(Const.URL_LOGIN_BY_CODE)
                .showLoading(true)
                .param("phone", phoneNumber)
                .param("code", code)
                .param("password", "")
                .build()
                .model(new ResponseCallback<LoginModel>() {
                    @Override
                    public void success(LoginModel model) {
                        ToastUtils.showToast(mContext, "登录成功");
                        SPUtils.getInstance().save(Const.ACCESS_USER_TOKEN, model.getToken());
                    }

                    @Override
                    public void failure(int code, String msg) {
                        ToastUtils.showToast(mContext, TextUtils.isEmpty(msg) ? "登录失败" : msg);
                    }
                });
    }

    /**
     * 获取短信验证码
     * @param phone
     */
    private void getPhoneCode(String phone) {
        new NetUtils.Builder()
                .context(mContext)
                .url(Const.URL_GET_PHONE_CODE)
                .showLoading(true)
                .param("phone", phone)
                .build()
                .model(new ResponseCallback<List<String>>() {
                    @Override
                    public void success(List<String> models) {
                        ToastUtils.showToast(mContext, "发送验证码成功");
                        tvGetCode.setEnabled(false);
                        updateTimer(120);
                        getCodeTimer.start();
                        //保存120s后的时间
                        SPUtils.getInstance().save("login_endtime", System.currentTimeMillis() + 120 * 1000);
                    }

                    @Override
                    public void failure(int code, String msg) {
                        ToastUtils.showToast(mContext, TextUtils.isEmpty(msg) ? "发送验证码失败" : msg);
                    }
                });
    }
}
