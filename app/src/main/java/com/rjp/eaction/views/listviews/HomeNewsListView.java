package com.rjp.eaction.views.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.views.base_listview.RefreshListView;

import java.util.List;

/**
 * author : Gimpo create on 2018/7/18 18:57
 * email  : jimbo922@163.com
 */
public class HomeNewsListView extends RefreshListView<String> {
    public HomeNewsListView(Context context) {
        super(context);
    }

    public HomeNewsListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<String>(mContext, R.layout.view_empty_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {

            }
        };
    }

    @Override
    protected void requestData() {
        new NetUtils.Builder()
                .context(mContext)
                .url("")
                .param("", "")
                .showLoading(true)
                .build()
                .model(new ResponseCallback<List<String>>() {
                    @Override
                    public void success(List<String> model) {

                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });

    }
}
