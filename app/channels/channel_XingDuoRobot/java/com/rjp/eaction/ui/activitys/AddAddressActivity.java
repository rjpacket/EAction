package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.AddressModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import com.rjp.eaction.views.other.SelectAddressView;

import java.io.Serializable;

import static com.rjp.eaction.network.UrlConst.URL_USER_ADD_ADDRESS;
import static com.rjp.eaction.ui.activitys.SelectAddressAreaActivity.RESULT_SELECT_AREA_SUCCESS;

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;
    @BindView(R.id.iv_default)
    ImageView ivDefault;
    @BindView(R.id.tv_select_area)
    TextView tvSelectArea;
    private String username;
    private String phone;
    private String address;
    private String selectArea;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, AddAddressActivity.class));
    }

    public static void trendTo(Context mContext, AddressModel addressModel) {
        Intent intent = new Intent(mContext, AddAddressActivity.class);
        intent.putExtra("address", addressModel);
        mContext.startActivity(intent);
    }

    @Override
    protected void handle() {
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("address")){
            AddressModel addressModel = (AddressModel) intent.getSerializableExtra("address");
            etUsername.setText(addressModel.getName());
            etPhone.setText(addressModel.getPhone());
            etDetailAddress.setText(addressModel.getAddress());
            tvSelectArea.setText(addressModel.getArea());
            ivDefault.setSelected("1".equals(addressModel.getIsdefault()));
        }
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
                startActivityForResult(new Intent(mContext, SelectAddressAreaActivity.class), 100);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_SELECT_AREA_SUCCESS){
            if(data != null && data.hasExtra("area")){
                selectArea = data.getStringExtra("area");
                tvSelectArea.setText(selectArea);
            }
        }
    }

    private void addAddress() {
        new NetUtils.Builder()
                .url(URL_USER_ADD_ADDRESS)
                .param("name", username)
                .param("phone", phone)
                .param("area", selectArea)
                .param("address", address)
                .param("isdefault", ivDefault.isSelected() ? "1" : "0")
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .context(mContext)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String models) {
                        Toast.makeText(mContext, models, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void failure(String code, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
