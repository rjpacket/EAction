package com.rjp.eaction.baseAF;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rjp.eaction.R;

import butterknife.ButterKnife;

/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private Context mContext;
    private FrameLayout layoutContainer;
    private NetworkView networkView;
    private TopBar topBar;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View baseRootView = inflater.inflate(R.layout.fragment_base, container, false);
        topBar = (TopBar) baseRootView.findViewById(R.id.top_bar);
        layoutContainer = (FrameLayout) baseRootView.findViewById(R.id.base_container);
        networkView = (NetworkView) baseRootView.findViewById(R.id.network_view);

        topBar.setVisibility(showTopBar() ? View.VISIBLE : View.GONE);
        topBar.setTitle(getPageTitle());
        topBar.setBackHidden(true);

        View childView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        layoutContainer.addView(childView);
        ButterKnife.bind(baseRootView);
        handle();
        return baseRootView;
    }

    /**
     * 网络请求失败，页面隐藏，显示重新加载布局
     */
    protected void setNetworkFail(String msg){
        layoutContainer.setVisibility(View.GONE);
        networkView.setNetworkFail(msg);
        networkView.setReloadListener(new ReloadListener() {
            @Override
            public void reload() {
                networkReload();
            }
        });
    }

    /**
     * 网络请求成功，界面显示出来
     */
    protected void setNetworkSuccess(){
        if(layoutContainer.getVisibility() == View.GONE) {
            layoutContainer.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 点击重新加载的按钮
     */
    protected abstract void networkReload();

    /**
     * 主要操作
     */
    protected abstract void handle();

    /**
     * 碎片标题
     * @return
     */
    protected abstract String getPageTitle();

    /**
     * 碎片是否显示标题栏
     * @return
     */
    private boolean showTopBar() {
        return true;
    }

    /**
     * 碎片布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 右上角第二个操作图标的源图片
     * @return
     */
    protected void setIcon1(int resId) {
        topBar.setIcon1(resId);
        topBar.setOnIcon1ClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnIcon1();
            }
        });
    }

    /**
     * 点击了icon1
     */
    protected void clickOnIcon1() {

    }

    /**
     * 右上角第一个操作图标的源图片
     * @return
     */
    protected void setIcon0(int resId) {
        topBar.setIcon0(resId);
        topBar.setOnIcon0ClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnIcon0();
            }
        });
    }

    /**
     * 点击了icon0
     */
    protected void clickOnIcon0() {

    }

    @Override
    public void onClick(View v) {

    }
}
