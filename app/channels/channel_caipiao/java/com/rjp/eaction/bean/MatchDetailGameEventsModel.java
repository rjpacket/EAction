package com.rjp.eaction.bean;

/**
 * Created by small on 2018/7/24.
 */

public class MatchDetailGameEventsModel {

    /**
     * teamName :
     * ishost : 0
     * playerName : 雷纳
     * eventTime : 49
     * eventType : 2
     */

    private String teamName;
    private String ishost;
    private String playerName;
    private String eventTime;
    private String eventType;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getIshost() {
        return ishost;
    }

    public void setIshost(String ishost) {
        this.ishost = ishost;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
