package com.rjp.eaction.bean;

/**
 * Created by small on 2018/7/25.
 */

public class OpenPrizeModel {

    /**
     * awardNo : 6 2 9 0 6
     * awardTime : 2018-07-25 21:51:12
     * extra : null
     * gameEn : ssc
     * luckyBlue :
     * periodName : 180725095
     * totalPool : 0.00
     * totalSale : 0.00
     */

    private String awardNo;
    private String awardTime;
    private Object extra;
    private String gameEn;
    private String luckyBlue;
    private String periodName;
    private String totalPool;
    private String totalSale;

    public String getAwardNo() {
        return awardNo;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public String getGameEn() {
        return gameEn;
    }

    public void setGameEn(String gameEn) {
        this.gameEn = gameEn;
    }

    public String getLuckyBlue() {
        return luckyBlue;
    }

    public void setLuckyBlue(String luckyBlue) {
        this.luckyBlue = luckyBlue;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getTotalPool() {
        return totalPool;
    }

    public void setTotalPool(String totalPool) {
        this.totalPool = totalPool;
    }

    public String getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(String totalSale) {
        this.totalSale = totalSale;
    }
}
