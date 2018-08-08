package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.bean.JCZQBiFenGroupModel;
import com.rjp.eaction.bean.JCZQBiFenModel;
import com.rjp.eaction.bean.MatchDetailGameEventsModel;
import com.rjp.eaction.bean.MatchDetailInjuredEventsModel;
import com.rjp.eaction.bean.MatchDetailPlayerModel;
import com.rjp.eaction.bean.TabEntity;
import com.rjp.eaction.event.MessageEvent;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.fragments.JCZQSingleLiveFragment;
import com.rjp.eaction.ui.fragments.MatchDetailCompetitionFragment;
import com.rjp.eaction.ui.fragments.MatchDetailGainsFragment;
import com.rjp.eaction.ui.fragments.MatchDetailOddsFragment;
import com.rjp.eaction.ui.fragments.MatchDetailRanksFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;

public class MatchDetailActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.match_detail_view_pager)
    ViewPager viewPager;
    private List<Fragment> fragments;
    private int currentIndex;

    public static void trendTo(Context context) {
        Intent intent = new Intent(context, MatchDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected String getPageTitle() {
        return "对阵详情";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match_detail;
    }

    @Override
    protected void handle() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("赛事"));
        tabEntities.add(new TabEntity("战绩"));
        tabEntities.add(new TabEntity("积分"));
        tabEntities.add(new TabEntity("赔率"));
        fragments = new ArrayList<>();
        fragments.add(MatchDetailCompetitionFragment.getInstance());
        fragments.add(MatchDetailGainsFragment.getInstance("1410998", "20586", "20585"));
        fragments.add(MatchDetailRanksFragment.getInstance());
        fragments.add(MatchDetailOddsFragment.getInstance());
        tabLayout.setTabData(tabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        requestData("1410998", "20586", "20585");
    }

    private void showFragment(int position) {
        if(currentIndex != position){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments.get(currentIndex));
            if (!fragments.get(position).isAdded()) {
                transaction.add(R.id.layout_container, fragments.get(position));
            }
            transaction.show(fragments.get(position)).commit();
            currentIndex = position;
        }
    }

    private void requestData(String matchId, String hostId, String visitId) {
        new NetUtils.Builder()
                .context(mContext)
                .url("biFen_matchDetail.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&type=10&gameEn=jczq&matchId=" + matchId + "&hostId=" + hostId + "&visitId=" + visitId)
                .build()
                .originModel(WANGYI_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String response) {
                        JSONObject result = JSONObject.parseObject(response);
                        JSONArray data = result.getJSONArray("data");
                        JSONObject jsonObject = data.getJSONObject(0);
                        List<MatchDetailGameEventsModel> visitTimelineEvents = JSONArray.parseArray(jsonObject.getString("visitTimelineEvents"), MatchDetailGameEventsModel.class);
                        List<MatchDetailGameEventsModel> hostTimelineEvents = JSONArray.parseArray(jsonObject.getString("hostTimelineEvents"), MatchDetailGameEventsModel.class);
                        List<MatchDetailGameEventsModel> gameEventsModels = new ArrayList<>();
                        gameEventsModels.addAll(visitTimelineEvents);
                        gameEventsModels.addAll(hostTimelineEvents);
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.MATCH_DETAIL_GAME_EVENTS, gameEventsModels));

                        List<MatchDetailInjuredEventsModel> visitInjuries = JSONArray.parseArray(jsonObject.getString("visitInjuries"), MatchDetailInjuredEventsModel.class);
                        List<MatchDetailInjuredEventsModel> hostInjuries = JSONArray.parseArray(jsonObject.getString("hostInjuries"), MatchDetailInjuredEventsModel.class);
                        List<MatchDetailInjuredEventsModel> injuredEventsModels = new ArrayList<>();
                        injuredEventsModels.addAll(visitInjuries);
                        injuredEventsModels.addAll(hostInjuries);
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.MATCH_DETAIL_INJURED_EVENTS, injuredEventsModels));

                        List<MatchDetailPlayerModel> allPlayers = new ArrayList<>();
                        JSONArray lineup = jsonObject.getJSONArray("lineup");
                        for (int i = 0; i < lineup.size(); i++) {
                            JSONObject players = lineup.getJSONObject(i);
                            List<MatchDetailPlayerModel> playerModels = JSONArray.parseArray(players.getString("players"), MatchDetailPlayerModel.class);
                            allPlayers.addAll(playerModels);
                        }
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.MATCH_DETAIL_PLAYERS, allPlayers));
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
