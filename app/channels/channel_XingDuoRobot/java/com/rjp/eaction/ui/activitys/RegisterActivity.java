package com.rjp.eaction.ui.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

import static com.rjp.eaction.network.UrlConst.URL_REGISTER;
import static com.rjp.eaction.network.UrlConst.URL_REGISTER_GET_CODE;

public class RegisterActivity extends BaseActivity {

    private static final int SEND_CODE_TIME = 30;  // 60s 的发送时间间隔
    @BindView(R.id.iv_register_logo)
    ImageView ivRegisterLogo;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.tv_register_send_code)
    TextView tvRegisterSendCode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.et_register_password_again)
    EditText etRegisterPasswordAgain;
    private int tickTime;
    private Timer timer;
    private String phone;
    private String code;
    private String password;
    private String rePassword;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, RegisterActivity.class));
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
        return R.layout.activity_register;
    }

    @Override
    protected String getPageTitle() {
        return "注册";
    }

    @OnClick({R.id.tv_register_send_code, R.id.tv_register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_send_code:
                phone = etRegisterPhone.getText().toString().trim();
                if (checkPhone(phone)) {
                    tvRegisterSendCode.setEnabled(false);
                    getRegisterCode(phone);
                } else {
                    Toast.makeText(mContext, "手机号码不合法", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register_button:
                code = etRegisterCode.getText().toString().trim();
                password = etRegisterPassword.getText().toString().trim();
                rePassword = etRegisterPasswordAgain.getText().toString().trim();
                registerAppUser();
                break;
        }
    }

    private void getRegisterCode(String phone) {
        new NetUtils.Builder()
                .context(mContext)
                .url(URL_REGISTER_GET_CODE)
                .param("mobile", phone)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {
                        Toast.makeText(mContext, model, Toast.LENGTH_SHORT).show();
                        startTimeTick();
                    }

                    @Override
                    public void failure(String code, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        tvRegisterSendCode.setEnabled(true);
                    }
                });
    }

    private void startTimeTick() {
        tickTime = SEND_CODE_TIME;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tickTime--;
                mHandler.obtainMessage(0).sendToTarget();
            }
        }, 1000, 1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(tickTime > 0) {
                tvRegisterSendCode.setText(String.format("%ss", tickTime));
            }else{
                timer.cancel();
                tvRegisterSendCode.setText("重新获取");
                tvRegisterSendCode.setEnabled(true);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 验证手机号的合法性
     *
     * @param phone
     * @return
     */
    private boolean checkPhone(String phone) {
        String telRegex = "[1]\\d{10}";//"[1]"代表第1位为数字1，"\\d{10}"代表后面是可以是0～9的数字，有10位。
        if (TextUtils.isEmpty(phone))
            return false;
        else
            return phone.matches(telRegex);
    }

    private void registerAppUser() {
        new NetUtils.Builder()
                .context(mContext)
                .url(URL_REGISTER)
//                .param("userName", phone)
                .param("userPassword", password)
                .param("userMobile", phone)
                .param("code", code)
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
