package com.rjp.eaction.bean;

import java.util.List;

/**
 * Created by small on 2018/8/6.
 */

public class OddsDetailModel {

    /**
     * updateTime : 赛前1小时11分
     * oddsFloat : [1,-1,-1]
     * oddsFirst : 1.57
     * oddsThird : 6.50
     * oddsSecond : 3.54
     */

    private String updateTime;
    private String oddsFirst;
    private String oddsThird;
    private String oddsSecond;
    private List<Integer> oddsFloat;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOddsFirst() {
        return oddsFirst;
    }

    public void setOddsFirst(String oddsFirst) {
        this.oddsFirst = oddsFirst;
    }

    public String getOddsThird() {
        return oddsThird;
    }

    public void setOddsThird(String oddsThird) {
        this.oddsThird = oddsThird;
    }

    public String getOddsSecond() {
        return oddsSecond;
    }

    public void setOddsSecond(String oddsSecond) {
        this.oddsSecond = oddsSecond;
    }

    public List<Integer> getOddsFloat() {
        return oddsFloat;
    }

    public void setOddsFloat(List<Integer> oddsFloat) {
        this.oddsFloat = oddsFloat;
    }
}
