package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.listviews.JCZQLiveListView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JCZQSingleLiveFragment extends BaseFragment {
    @BindView(R.id.jczq_live_list_view)
    JCZQLiveListView jczqLiveListView;

    public JCZQSingleLiveFragment() {
        // Required empty public constructor
    }

    public static JCZQSingleLiveFragment getInstance(String type){
        JCZQSingleLiveFragment homeNewsFragment = new JCZQSingleLiveFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        homeNewsFragment.setArguments(args);
        return homeNewsFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jczqlive;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {
        Bundle arguments = getArguments();
        if(arguments.containsKey("type")){
            String type = arguments.getString("type");
            jczqLiveListView.requestData("jczq", type);
        }
    }

}
