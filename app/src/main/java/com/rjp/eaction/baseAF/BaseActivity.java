package com.rjp.eaction.baseAF;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public Context mContext;
    private TopBar topBar;
    private NetworkView networkView;
    private FrameLayout layoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = this;
        topBar = (TopBar) findViewById(R.id.top_bar);
        layoutContainer = (FrameLayout) findViewById(R.id.layout_container);
        networkView = (NetworkView) findViewById(R.id.network_view);

        topBar.setVisibility(showTopBar() ? View.VISIBLE : View.GONE);
        topBar.setTitle(getPageTitle());
        topBar.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行onBackPressed 防止子类需要特殊操作
                onBackPressed();
            }
        });

        View childRootView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        layoutContainer.addView(childRootView);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        handle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(MessageEvent event){

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
     * 网络重新加载
     */
    protected abstract void networkReload();

    /**
     * 获取子类布局
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

    /**
     * 页面的标题
     * @return
     */
    protected String getPageTitle() {
        return getString(R.string.app_name);
    }

    /**
     * 修改页面的标题
     * @param title
     */
    protected void setTopTitle(String title) {
        topBar.setTitle(title);
    }

    /**
     * 默认每一个activity需要标题栏
     * @return
     */
    protected boolean showTopBar() {
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    protected abstract void handle();
}
