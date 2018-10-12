package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.views.decorations.SimplePaddingDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity extends BaseActivity {

    @BindView(R.id.book_recycler_view)
    RecyclerView bookRecyclerView;

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, BookDetailActivity.class));
    }

    @Override
    protected void handle() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bookRecyclerView.setLayoutManager(manager);
        bookRecyclerView.addItemDecoration(new SimplePaddingDecoration(mContext, AppUtils.dp2px(10)));
        ArrayList<String> datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        bookRecyclerView.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_book_detail_link_recycler_view, datas) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                LinearLayout llBookContainer = holder.getView(R.id.book_container);
                int screenWidth = AppUtils.getScreenWidth(mContext) - AppUtils.dp2px(40);
                int bookWidth = screenWidth / 3;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(bookWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                llBookContainer.setLayoutParams(params);
            }
        });
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected String getPageTitle() {
        return "书架详情";
    }

}
