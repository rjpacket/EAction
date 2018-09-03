package com.rjp.eaction.base;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemplateFragment extends BaseFragment {

    public TemplateFragment() {
        // Required empty public constructor
    }

    public static TemplateFragment getInstance(){
        TemplateFragment fragment = new TemplateFragment();
        return fragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {

    }
}
