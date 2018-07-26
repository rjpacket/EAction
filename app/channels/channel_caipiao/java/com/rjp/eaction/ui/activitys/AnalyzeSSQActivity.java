package com.rjp.eaction.ui.activitys;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.rjp.eaction.bean.TabEntity;

import java.util.ArrayList;

public class AnalyzeSSQActivity extends AnalyzeBaseActivity {

    @Override
    protected ArrayList<CustomTabEntity> getTabEntities() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("开奖"));
        tabEntities.add(new TabEntity("红球冷热"));
        tabEntities.add(new TabEntity("蓝球冷热"));
        return tabEntities;
    }

    @Override
    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        return fragments;
    }
}
