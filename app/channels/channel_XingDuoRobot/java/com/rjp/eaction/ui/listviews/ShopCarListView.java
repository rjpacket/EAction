package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.views.base_listview.LoadMoreListView;
import com.rjp.eaction.views.base_listview.RefreshListView;

/**
 * Created by small on 2018/8/21.
 */

public class ShopCarListView extends RefreshListView<String> {
    public ShopCarListView(Context context) {
        super(context);
    }

    public ShopCarListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        return new CommonAdapter<String>(mContext, R.layout.item_shop_car_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String s, int i) {

            }
        };
    }

    @Override
    protected void requestData() {

    }
}
