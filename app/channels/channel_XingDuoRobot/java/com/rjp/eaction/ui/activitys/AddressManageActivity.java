package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;
import butterknife.BindView;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

import java.util.ArrayList;

import static com.rjp.eaction.util.AppUtils.dp2px;

public class AddressManageActivity extends BaseActivity {

    @BindView(R.id.swipe_menu_list_view)
    SwipeMenuListView swipeMenuListView;
    private ArrayList<String> models;
    private SwipeMenuCreator creator;

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, AddressManageActivity.class));
    }

    @Override
    protected void handle() {

    }

    private void initSwipeMenuList() {
        models = new ArrayList<>();
        models.add("");
        models.add("");
        models.add("");
        models.add("");
        swipeMenuListView.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_devices_manage_list_view, models) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {

            }
        });
        creatSwipeMenu();
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
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

}