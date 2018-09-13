package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.rjp.eaction.network.UrlConst.URL_USER_ADD_ADDRESS;

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;
    @BindView(R.id.iv_default)
    ImageView ivDefault;
    private String username;
    private String phone;
    private String address;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, AddAddressActivity.class));
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected String getPageTitle() {
        return "新增地址";
    }

    @OnClick({R.id.iv_default, R.id.ll_select_area, R.id.ll_set_default_address, R.id.tv_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_default:
                ivDefault.setSelected(!ivDefault.isSelected());
                break;
            case R.id.ll_select_area:
                break;
            case R.id.ll_set_default_address:
                break;
            case R.id.tv_add_address:
                username = etUsername.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                address = etDetailAddress.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(mContext, "收货人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(mContext, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(mContext, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                addAddress();
                break;
        }
    }

    private void addAddress() {
        new NetUtils.Builder()
                .url(URL_USER_ADD_ADDRESS)
                .param("name", username)
                .param("phone", phone)
                .param("area", "abc")
                .param("address", address)
                .param("isdefault", ivDefault.isSelected() ? "0" : "1")
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .context(mContext)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String models) {

                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }
}
