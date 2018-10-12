package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import butterknife.BindView;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.interfaces.OnSelectAreaSuccessListener;
import com.rjp.eaction.views.other.SelectAddressView;

public class SelectAddressAreaActivity extends BaseActivity implements OnSelectAreaSuccessListener {
    public static final int RESULT_SELECT_AREA_SUCCESS = 2020;

    @BindView(R.id.select_address_view)
    SelectAddressView selectAddressView;

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, SelectAddressAreaActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "配送区域";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_address_area;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        selectAddressView.setOnSelectAreaSuccessListener(this);
    }

    @Override
    public void onSelectAreaSuccess(String area) {
        Intent data = new Intent();
        data.putExtra("area", area);
        setResult(RESULT_SELECT_AREA_SUCCESS, data);
        finish();
    }
}
