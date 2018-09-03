package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.ui.activitys.StoreDetailActivity;
import com.rjp.eaction.views.base_listview.LoadMoreListView;

/**
 * Created by small on 2018/8/21.
 */

public class AlreadyReadBookListView extends LoadMoreListView<String> {
    public AlreadyReadBookListView(Context context) {
        super(context);
    }

    public AlreadyReadBookListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        return new CommonAdapter<String>(mContext, R.layout.item_already_read_book_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String s, int i) {

            }
        };
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StoreDetailActivity.trendTo(mContext);
    }
}
