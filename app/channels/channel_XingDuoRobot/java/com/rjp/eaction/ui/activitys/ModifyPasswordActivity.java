package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import static com.rjp.eaction.network.UrlConst.URL_LOGOUT;
import static com.rjp.eaction.network.UrlConst.URL_USER_MODIFY_PASSWORD;

public class ModifyPasswordActivity extends BaseActivity {

    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_repwd)
    EditText etNewRepwd;
    private String oldPwd;
    private String newPwd;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, ModifyPasswordActivity.class));
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected String getPageTitle() {
        return "修改密码";
    }

    @OnClick({R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                oldPwd = etOldPwd.getText().toString().trim();
                newPwd = etNewPwd.getText().toString().trim();
                String newRepwd = etNewRepwd.getText().toString().trim();
                if(!newPwd.equals(newRepwd)){
                    Toast.makeText(mContext, "前后两次输入新密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                modifyPassword();
                break;
        }
    }

    private void modifyPassword() {
        new NetUtils.Builder()
                .context(mContext)
                .url(URL_USER_MODIFY_PASSWORD)
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .param("oldpassword", oldPwd)
                .param("newpassword", newPwd)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {
                        Toast.makeText(mContext, "修改密码成功，请重新登录", Toast.LENGTH_SHORT).show();
                        SPUtils.getInstance(mContext).clear();
                        LoginActivity.trendTo(mContext);
                        finish();
                    }

                    @Override
                    public void failure(String code, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
