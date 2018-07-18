package com.rjp.eaction.ui.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.login.LoginActivity;
import com.rjp.eaction.views.other.CircleImageView;
import com.rjp.eaction.views.other.IScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.ll_mine_top) LinearLayout llMineTop;
    @BindView(R.id.tv_top_bar) TextView tvTopBar;
    @BindView(R.id.iv_user_image) CircleImageView ivUserImage;
    @BindView(R.id.tv_user_name) TextView tvUserName;
    @BindView(R.id.tv_user_signature) TextView tvUserSignature;
    @BindView(R.id.mine_scroll_view) IScrollView mineScrollView;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        tvTopBar.setAlpha(0);

        LoginActivity.trendTo(mContext);

        mineScrollView.setScrollChangeListener(new IScrollView.IScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                int scrollY = mineScrollView.getScrollY();
                int topHeight = llMineTop.getMeasuredHeight();
                if(scrollY > topHeight){
                    scrollY = topHeight;
                }
                tvTopBar.setAlpha((float) (scrollY * 1.0 / topHeight));
            }
        });
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "我的";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
