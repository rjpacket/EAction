package com.rjp.eaction.bean;

/**
 * Created by small on 2018/7/24.
 */

public class MatchDetailCurrentScoreModel {

    /**
     * leagueName : 瑞典超
     * result : 胜
     * hostName : 哈马比
     * visitName : 厄斯特松
     * score : 1:2
     * beginTime : 2018-07-10
     */

    /**
     * {
     "leagueName": "墨超",
     "hostName": "美洲狮",
     "visitName": "内卡萨",
     "score": "5:3",
     "halfScore": "1:1",
     "beginTime": "2018-07-30"
     }
     */

    private String leagueName;
    private String result;
    private String hostName;
    private String visitName;
    private String score;
    private String halfScore;
    private String beginTime;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getHalfScore() {
        return halfScore;
    }

    public void setHalfScore(String halfScore) {
        this.halfScore = halfScore;
    }
}
