package com.rjp.eaction.ui.fragments;


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
        TemplateFragment testFragment = new TemplateFragment();
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
        return "";
    }

    @Override
    protected void handle() {

    }
}
