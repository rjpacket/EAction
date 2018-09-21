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
import com.rjp.eaction.bean.HomeBookModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.activitys.StoreDetailActivity;
import com.rjp.eaction.views.base_listview.LoadMoreListView;

import java.util.List;

import static com.rjp.eaction.network.UrlConst.URL_HOME_BOOK_LIST;
import static com.rjp.eaction.network.UrlConst.URL_STORE_CATEGORY_GOODS;

/**
 * Created by small on 2018/8/21.
 */

public class StoreListView extends LoadMoreListView<String> {
    private String categoryId;

    public StoreListView(Context context) {
        super(context);
    }

    public StoreListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<String>(mContext, R.layout.item_store_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String s, int i) {

            }
        };
    }

    public void requestData(String categoryId){
        this.categoryId = categoryId;
        resetFirstPage();
        requestData();
    }

    @Override
    protected void requestData() {
        new NetUtils.Builder()
                .url(URL_STORE_CATEGORY_GOODS)
                .context(mContext)
                .param("bookClassifyId", categoryId)
                .param("page", String.valueOf(mPage))
                .param("rows", String.valueOf(mPageSize))
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String models) {
//                        dealSuccessData(models);
                    }

                    @Override
                    public void failure(String code, String msg) {
                        dealFailureData();
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StoreDetailActivity.trendTo(mContext);
    }
}
