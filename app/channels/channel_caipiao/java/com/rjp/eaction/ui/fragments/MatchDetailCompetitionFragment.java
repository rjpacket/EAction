package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.MatchDetailGameEventsModel;
import com.rjp.eaction.bean.MatchDetailInjuredEventsModel;
import com.rjp.eaction.bean.MatchDetailPlayerModel;
import com.rjp.eaction.event.MessageEvent;
import com.rjp.eaction.ui.views.MatchDetailGameEventsView;
import com.rjp.eaction.ui.views.MatchDetailInjuredEventsView;
import com.rjp.eaction.ui.views.MatchDetailPlayerView;
import com.zhy.adapter.recyclerview.CommonAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rjp.eaction.event.MessageEvent.MATCH_DETAIL_GAME_EVENTS;
import static com.rjp.eaction.event.MessageEvent.MATCH_DETAIL_INJURED_EVENTS;
import static com.rjp.eaction.event.MessageEvent.MATCH_DETAIL_PLAYERS;

/**
 * 赛事
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailCompetitionFragment extends BaseFragment {

    @BindView(R.id.game_view)
    MatchDetailGameEventsView gameView;
    @BindView(R.id.injured_view)
    MatchDetailInjuredEventsView injuredView;
    @BindView(R.id.player_view)
    MatchDetailPlayerView playerView;

    public MatchDetailCompetitionFragment() {
        // Required empty public constructor
    }

    public static MatchDetailCompetitionFragment getInstance(){
        MatchDetailCompetitionFragment testFragment = new MatchDetailCompetitionFragment();
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_detail_competition;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {
        gameView.setTitle("比赛事件");
        injuredView.setTitle("伤停信息");
        playerView.setTitle("阵容信息");
    }

    @Subscribe
    public void onEvent(MessageEvent event){
        String action = event.getAction();
        if(MATCH_DETAIL_GAME_EVENTS.equals(action)){
            gameView.bindData((List<MatchDetailGameEventsModel>) event.getData());
        }else if(MATCH_DETAIL_INJURED_EVENTS.equals(action)){
            injuredView.bindData((List<MatchDetailInjuredEventsModel>) event.getData());
        }else if(MATCH_DETAIL_PLAYERS.equals(action)){
            playerView.bindData((List<MatchDetailPlayerModel>) event.getData());
        }
    }
}
