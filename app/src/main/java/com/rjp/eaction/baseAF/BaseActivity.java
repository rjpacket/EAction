package com.rjp.eaction.baseAF;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjp.eaction.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private Context mContext;
    private ImageView ivIcon0;
    private ImageView ivIcon1;
    private NetworkView networkView;
    private FrameLayout layoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = this;
        RelativeLayout titleBar = (RelativeLayout) findViewById(R.id.title_bar);
        ImageView ivBaseBack = (ImageView) findViewById(R.id.iv_base_back);
        TextView tvBaseTitle = (TextView) findViewById(R.id.tv_base_title);
        ivIcon0 = (ImageView) findViewById(R.id.iv_base_icon0);
        ivIcon1 = (ImageView) findViewById(R.id.iv_base_icon1);
        layoutContainer = (FrameLayout) findViewById(R.id.layout_container);
        networkView = (NetworkView) findViewById(R.id.network_view);

        titleBar.setVisibility(showTitleBar() ? View.VISIBLE : View.GONE);
        tvBaseTitle.setText(getPageTitle());
        ivBaseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行onBackPressed 防止子类需要特殊操作
                onBackPressed();
            }
        });

        View childRootView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        layoutContainer.addView(childRootView);
        ButterKnife.bind(this);
        handle();
    }

    protected abstract void handle();

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
        ivIcon1.setImageResource(resId);
        ivIcon1.setVisibility(View.VISIBLE);
        ivIcon1.setOnClickListener(new View.OnClickListener() {
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
        ivIcon0.setImageResource(resId);
        ivIcon0.setVisibility(View.VISIBLE);
        ivIcon0.setOnClickListener(new View.OnClickListener() {
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
     * 默认每一个activity需要标题栏
     * @return
     */
    protected boolean showTitleBar() {
        return true;
    }
}
