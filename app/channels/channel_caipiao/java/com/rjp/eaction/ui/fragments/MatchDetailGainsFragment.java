package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.MatchDetailCurrentScoreModel;
import com.rjp.eaction.bean.MatchDetailFutureGameModel;
import com.rjp.eaction.bean.MatchDetailGameEventsModel;
import com.rjp.eaction.bean.MatchDetailInjuredEventsModel;
import com.rjp.eaction.bean.MatchDetailNewsModel;
import com.rjp.eaction.bean.MatchDetailPlayerModel;
import com.rjp.eaction.event.MessageEvent;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.views.MatchDetailCurrentScoreView;
import com.rjp.eaction.ui.views.MatchDetailFutureGameView;
import com.rjp.eaction.ui.views.MatchDetailGameEventsView;
import com.rjp.eaction.ui.views.MatchDetailHistoryGameView;
import com.rjp.eaction.ui.views.MatchDetailNewsView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;

/**
 * 战绩
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailGainsFragment extends BaseFragment {

    @BindView(R.id.history_view)
    MatchDetailHistoryGameView historyView;
    @BindView(R.id.current_view)
    MatchDetailCurrentScoreView currentView;
    @BindView(R.id.future_view)
    MatchDetailFutureGameView futureView;
    @BindView(R.id.news_view)
    MatchDetailNewsView newsView;

    public MatchDetailGainsFragment() {
        // Required empty public constructor
    }

    public static MatchDetailGainsFragment getInstance(String matchId, String hostId, String visitId){
        MatchDetailGainsFragment testFragment = new MatchDetailGainsFragment();
        Bundle args = new Bundle();
        args.putString("matchId", matchId);
        args.putString("hostId", hostId);
        args.putString("visitId", visitId);
        testFragment.setArguments(args);
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_detail_gains;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {
        historyView.setTitle("历史交锋");
        currentView.setTitle("近期战绩");
        futureView.setTitle("未来赛事");
        newsView.setTitle("资讯");

        Bundle args = getArguments();
        if(args != null){
            String matchId = args.getString("matchId");
            String hostId = args.getString("hostId");
            String visitId = args.getString("visitId");
            requestData(matchId, hostId, visitId);
        }
    }

    private void requestData(String matchId, String hostId, String visitId) {
        new NetUtils.Builder()
                .context(mContext)
                .url("biFen_matchDetail.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&type=11&gameEn=jczq&matchId=" + matchId + "&hostId=" + hostId + "&visitId=" + visitId)
                .build()
                .originModel(WANGYI_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String response) {
                        JSONObject result = JSONObject.parseObject(response);
                        JSONArray data = result.getJSONArray("data");
                        JSONObject jsonObject = data.getJSONObject(0);
                        List<MatchDetailCurrentScoreModel> historyVS = JSONArray.parseArray(jsonObject.getString("historyVS"), MatchDetailCurrentScoreModel.class);
                        historyView.bindData(historyVS);

                        List<MatchDetailCurrentScoreModel> hostRecentMatch = JSONArray.parseArray(jsonObject.getString("hostRecentMatch"), MatchDetailCurrentScoreModel.class);
                        List<MatchDetailCurrentScoreModel> visitRecentMatch = JSONArray.parseArray(jsonObject.getString("visitRecentMatch"), MatchDetailCurrentScoreModel.class);
                        List<MatchDetailCurrentScoreModel> recentMatches = new ArrayList<>();
                        recentMatches.addAll(hostRecentMatch);
                        recentMatches.addAll(visitRecentMatch);
                        currentView.bindData(recentMatches);

                        List<MatchDetailFutureGameModel> hostFutureMatch = JSONArray.parseArray(jsonObject.getString("hostFutureMatch"), MatchDetailFutureGameModel.class);
                        List<MatchDetailFutureGameModel> visitFutureMatch = JSONArray.parseArray(jsonObject.getString("visitFutureMatch"), MatchDetailFutureGameModel.class);
                        List<MatchDetailFutureGameModel> futureMatches = new ArrayList<>();
                        futureMatches.addAll(hostFutureMatch);
                        futureMatches.addAll(visitFutureMatch);
                        futureView.bindData(futureMatches);

                        List<MatchDetailNewsModel> newsModels = JSONArray.parseArray(jsonObject.getString("news"), MatchDetailNewsModel.class);
                        newsView.bindData(newsModels);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
