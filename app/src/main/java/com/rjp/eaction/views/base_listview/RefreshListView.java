package com.rjp.eaction.views.base_listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.rjp.baselistview.simple.SimpleListView;
import com.rjp.eaction.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

import java.util.List;

/**
 * author : Gimpo create on 2018/7/18 18:59
 * email  : jimbo922@163.com
 */
public abstract class RefreshListView<T> extends SimpleListView<T> {
    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void filterData(List<T> mDatas) {

    }

    @Override
    protected boolean isFirstPage() {
        return mPage == 0;
    }

    @Override
    protected boolean hasMoreData(List<T> list) {
        return list.size() >= mPageSize;
    }

    @Override
    protected void pageNext() {
        mPage ++;
    }

    @Override
    protected void resetFirstPage() {
        mPage = 0;
        mPageSize = 10;
    }

    @Override
    protected RefreshFooter getRefreshFooter() {
        return null;
    }

    @Override
    protected RefreshHeader getRefreshHeader() {
        return new EActionRefreshHeader(mContext);
    }

    @Override
    protected View getEmptyView() {
        return LayoutInflater.from(mContext).inflate(R.layout.view_empty_view, null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
