package com.rjp.eaction.bean;

/**
 * Created by small on 2018/7/24.
 */

public class MatchDetailFutureGameModel {

    /**
     * leagueName : 瑞典超
     * hostName : 埃夫斯堡
     * visitName : 厄斯特松
     * gap : 14
     * beginTime : 2018-08-07
     */

    private String leagueName;
    private String hostName;
    private String visitName;
    private int gap;
    private String beginTime;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}
