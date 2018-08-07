package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseFragment {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private int count;

    public TestFragment() {
        // Required empty public constructor
    }

    public static TestFragment getInstance(int count){
        TestFragment testFragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt("count", count);
        testFragment.setArguments(args);
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected String getPageTitle() {
        return "开奖记录";
    }

    @Override
    protected void handle() {
        Bundle arguments = getArguments();
        if(arguments != null && arguments.containsKey("count")){
            count = arguments.getInt("count");
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        //设置Adapter
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            strings.add("");
        }
        RecyclerView.Adapter adapter = new CommonAdapter<String>(mContext, R.layout.item_open_prize_jczq_list_view, strings) {

            @Override
            protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, String s, int position) {

            }
        };
        //设置分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}
