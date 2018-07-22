package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.RecommendMatchModel;
import com.rjp.eaction.network.retrofit.ApiService;
import com.rjp.eaction.request.NetSuccessCallback;
import com.rjp.eaction.request.RequestUtils;
import com.rjp.eaction.ui.views.RecommendMatchesView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment {

    @BindView(R.id.ll_recommend_matches_container)
    LinearLayout llMatchesContainer;

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
                JSONObject jsonObject = JSONObject.parseObject(result);
                String matchesJson = jsonObject.getString("matchInfos");
                List<RecommendMatchModel> models = JSONObject.parseArray(matchesJson, RecommendMatchModel.class);
                addMatches(models);
            }
        });
    }

    private void addMatches(List<RecommendMatchModel> models) {
        llMatchesContainer.removeAllViews();
        for (RecommendMatchModel model : models) {
            RecommendMatchesView recommendMatchesView = new RecommendMatchesView(mContext);
            recommendMatchesView.bindData(model);
            llMatchesContainer.addView(recommendMatchesView);
        }
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
