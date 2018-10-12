package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.AddressModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import static com.rjp.eaction.network.UrlConst.URL_USER_ALL_ADDRESS;
import static com.rjp.eaction.util.AppUtils.dp2px;

public class AddressManageActivity extends BaseActivity {

    @BindView(R.id.swipe_menu_list_view)
    SwipeMenuListView swipeMenuListView;
    private ArrayList<AddressModel> models;
    private SwipeMenuCreator creator;
    private CommonAdapter<AddressModel> adapter;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, AddressManageActivity.class));
    }

    @Override
    protected void handle() {
        initSwipeMenuList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserAddressList();
    }

    private void getUserAddressList() {
        new NetUtils.Builder()
                .url(URL_USER_ALL_ADDRESS)
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .context(mContext)
                .build()
                .model(new ResponseCallback<List<AddressModel>>() {
                    @Override
                    public void success(List<AddressModel> datas) {
                        models.clear();
                        models.addAll(datas);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }

    private void initSwipeMenuList() {
        models = new ArrayList<>();
        swipeMenuListView.setAdapter(adapter = new CommonAdapter<AddressModel>(mContext, R.layout.item_address_manage_list_view, models) {
            @Override
            protected void convert(ViewHolder viewHolder, AddressModel item, int position) {
                viewHolder.setText(R.id.tv_name, String.format("收货人：%s", item.getName()));
                viewHolder.setText(R.id.tv_phone, item.getPhone());
                viewHolder.setText(R.id.tv_address, String.format("收货地址：%s", item.getArea() + item.getAddress()));
                View view = viewHolder.getView(R.id.tv_default);
                view.setVisibility("1".equals(item.getIsdefault()) ? View.VISIBLE : View.GONE);
            }
        });
        creatSwipeMenu();
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        swipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddressModel addressModel = models.get(position);
                AddAddressActivity.trendTo(mContext, addressModel);
            }
        });
    }

    private void creatSwipeMenu() {
        creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(mContext);
                openItem.setBackground(new ColorDrawable(Color.parseColor("#ffeb1c42")));
                openItem.setWidth(dp2px(70));
                openItem.setTitle("删除");
                openItem.setTitleSize(16);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }

        };

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_manage;
    }

    @Override
    protected String getPageTitle() {
        return "地址管理";
    }

    @OnClick({R.id.tv_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_address:
                AddAddressActivity.trendTo(mContext);
                break;
        }
    }
}
