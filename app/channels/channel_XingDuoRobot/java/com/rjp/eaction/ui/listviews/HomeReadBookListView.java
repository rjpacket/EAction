package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import android.widget.Toast;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.HomeBookModel;
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

public class HomeReadBookListView extends LoadMoreListView<HomeBookModel> {
    private String categoryId;

    public HomeReadBookListView(Context context) {
        super(context);
    }

    public HomeReadBookListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<HomeBookModel>(mContext, R.layout.item_home_read_book_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeBookModel model, int position) {

            }
        };
    }

    /**
     * 带请求参数
     * @param categoryId
     */
    public void requestData(String categoryId){
        this.categoryId = categoryId;
        resetFirstPage();
        requestData();
    }

    @Override
    protected void requestData() {
        new NetUtils.Builder()
                .url(URL_HOME_BOOK_LIST)
                .context(mContext)
                .param("bookClassifyId", categoryId)
                .param("page", String.valueOf(mPage))
                .param("rows", String.valueOf(mPageSize))
                .build()
                .model(new ResponseCallback<List<HomeBookModel>>() {
                    @Override
                    public void success(List<HomeBookModel> models) {
                        dealSuccessData(models);
                    }

                    @Override
                    public void failure(String code, String msg) {
                        dealFailureData();
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
