package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends BaseFragment {

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment getInstance(){
        StoreFragment fragment = new StoreFragment();
        return fragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {

    }
}
