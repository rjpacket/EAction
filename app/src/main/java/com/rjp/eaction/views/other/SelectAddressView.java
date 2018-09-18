package com.rjp.eaction.views.other;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.CityBean;
import com.rjp.eaction.bean.ProvinceBean;
import com.rjp.eaction.bean.TabEntity;
import com.rjp.eaction.ui.fragments.*;
import com.rjp.eaction.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择地址自定义ViewGroup
 */
public class SelectAddressView extends LinearLayout implements OnProvinceSelectListener, OnCitySelectListener, OnAreaSelectListener {
    private Context mContext;
    private CommonTabLayout tabLayout;
    private FragmentManager fragmentManager;
    private int currentIndex = 1;
    private List<Fragment> fragments;
    private ProvinceFragment provinceFragment;
    private CityFragment cityFragment;
    private AreaFragment areaFragment;
    private String selectedProvince;
    private String selectedCity;
    private String selectedArea;

    public SelectAddressView(Context context) {
        this(context, null);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        LayoutInflater.from(mContext).inflate(R.layout.view_select_address_view, this);
        ImageView ivClose = findViewById(R.id.iv_close_panel);
        tabLayout = findViewById(R.id.select_address_tab_layout);
        String cityJson = FileUtils.getAssetsFile(mContext, "city.json");
        List<ProvinceBean> provinceBeans = JSONObject.parseArray(cityJson, ProvinceBean.class);

        fragments = new ArrayList<>();
        provinceFragment = new ProvinceFragment();
        cityFragment = new CityFragment();
        areaFragment = new AreaFragment();

        provinceFragment.setOnProvinceSelectListener(this);
        cityFragment.setOnCitySelectListener(this);
        areaFragment.setOnAreaSelectListener(this);

        fragments.add(provinceFragment);
        fragments.add(cityFragment);
        fragments.add(areaFragment);

        showFragment(0);

        provinceFragment.setData(provinceBeans);
        ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
        tabEntitys.add(new TabEntity("请选择"));
        tabLayout.setTabData(tabEntitys);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                showFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void showFragment(int position) {
        if(currentIndex != position){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(fragments.get(currentIndex));
            if (!fragments.get(position).isAdded()) {
                transaction.add(R.id.city_container, fragments.get(position));
            }
            transaction.show(fragments.get(position)).commit();
            currentIndex = position;
        }
    }

    @Override
    public void onProvinceSelect(ProvinceBean provinceBean) {
        showFragment(1);
        cityFragment.setData(provinceBean.getCity());
        selectedProvince = provinceBean.getName();
        ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
        tabEntitys.add(new TabEntity(selectedProvince));
        tabEntitys.add(new TabEntity("请选择"));
        tabLayout.setTabData(tabEntitys);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tabLayout.setCurrentTab(1);
            }
        }, 300);
    }

    @Override
    public void onCitySelect(CityBean cityBean) {
        showFragment(2);
        areaFragment.setData(cityBean.getArea());
        selectedCity = cityBean.getName();
        ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
        tabEntitys.add(new TabEntity(selectedProvince));
        tabEntitys.add(new TabEntity(selectedCity));
        tabEntitys.add(new TabEntity("请选择"));
        tabLayout.setTabData(tabEntitys);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tabLayout.setCurrentTab(2);
            }
        }, 300);
    }

    @Override
    public void onAreaSelect(String area) {
        selectedArea = area;
        ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
        tabEntitys.add(new TabEntity(selectedProvince));
        tabEntitys.add(new TabEntity(selectedCity));
        tabEntitys.add(new TabEntity(selectedArea));
        tabLayout.setTabData(tabEntitys);
        Toast.makeText(mContext, "选择了" + selectedProvince + selectedCity + selectedArea, Toast.LENGTH_SHORT).show();
    }
}
