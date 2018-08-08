package com.rjp.eaction.event;

import com.rjp.eaction.bean.MatchDetailGameEventsModel;

import java.util.List;

/**
 * Created by small on 2018/8/8.
 */

public class MessageEvent {
    public static final String MATCH_DETAIL_GAME_EVENTS = "match_detail_game_events";
    public static final String MATCH_DETAIL_INJURED_EVENTS = "match_detail_injured_events";
    public static final String MATCH_DETAIL_PLAYERS = "match_detail_players";

    private String action;
    private Object object;

    public MessageEvent(String action){
        this.action = action;
    }

    public MessageEvent(String action, Object object){
        this.action = action;
        this.object = object;
    }

    public String getAction() {
        return this.action;
    }

    public Object getData() {
        return this.object;
    }
}
