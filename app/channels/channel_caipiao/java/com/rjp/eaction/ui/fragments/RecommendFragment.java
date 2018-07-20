package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.network.retrofit.ApiService;
import com.rjp.eaction.request.NetSuccessCallback;
import com.rjp.eaction.request.RequestUtils;
import com.rjp.eaction.ui.social.SendSocialActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment {


    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        getRecommendMatches();
    }

    private void getRecommendMatches() {
        String url = ApiService.WANGYI_URL + "getArenaHallInfo_jczq.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27";
        RequestUtils.getInstance().post(mContext, url, new JSONObject(), new NetSuccessCallback() {
            @Override
            public void onSuccess(String result) {

            }
        });
    }

    @Override
    protected String getPageTitle() {
        return "竞彩推荐";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

}
