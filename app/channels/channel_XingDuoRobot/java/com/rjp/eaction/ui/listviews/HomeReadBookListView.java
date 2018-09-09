package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.HomeCategoryModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.LogUtils;
import com.rjp.eaction.views.base_listview.LoadMoreListView;

import java.util.List;

import static com.rjp.eaction.network.UrlConst.URL_HOME_BOOK_LIST;
import static com.rjp.eaction.network.UrlConst.URL_HOME_CATEGORY;

/**
 * Created by small on 2018/8/21.
 */

public class HomeReadBookListView extends LoadMoreListView<String> {
    public HomeReadBookListView(Context context) {
        super(context);
    }

    public HomeReadBookListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<String>(mContext, R.layout.item_home_read_book_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String s, int i) {

            }
        };
    }

    @Override
    protected void resetFirstPage() {
        mPage = 1;
        mPageSize = 10;
    }

    @Override
    protected void requestData() {
        new NetUtils.Builder()
                .url(URL_HOME_BOOK_LIST)
                .context(mContext)
                .param("bookClassifyId", "1")
                .param("page", String.valueOf(mPage))
                .param("rows", String.valueOf(mPageSize))
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
