package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.activitys.BookDetailActivity;
import com.rjp.eaction.views.base_listview.LoadMoreListView;

import java.util.List;

/**
 * Created by small on 2018/8/21.
 */

public class ReadBookListView extends LoadMoreListView<String> {
    public ReadBookListView(Context context) {
        super(context);
    }

    public ReadBookListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<String>(mContext, R.layout.item_read_book_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String s, int i) {

            }
        };
    }

    @Override
    protected void requestData() {
        new NetUtils.Builder().url("book/findAll.jhtml")
                .context(mContext)
                .build()
                .model(new ResponseCallback<List<String>>() {
                    @Override
                    public void success(List<String> models) {
                        dealSuccessData(models);
                    }

                    @Override
                    public void failure(String code, String msg) {
                        dealFailureData();
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookDetailActivity.trendTo(mContext);
    }
}
