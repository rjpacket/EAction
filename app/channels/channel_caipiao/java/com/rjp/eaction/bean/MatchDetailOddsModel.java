package com.rjp.eaction.bean;

import java.util.List;

/**
 * Created by small on 2018/8/7.
 */

public class MatchDetailOddsModel {
    private String firstHostOdds;
    private String nowHostOdds;
    private String firstVisitOdds;
    private String nowVisitOdds;
    private String firstDrawOdds;
    private String nowDrawOdds;
    private String companyId;
    private String companyCnName;
    private List<Integer> oddsFloat;

    public String getFirstHostOdds() {
        return firstHostOdds;
    }

    public void setFirstHostOdds(String firstHostOdds) {
        this.firstHostOdds = firstHostOdds;
    }

    public String getNowHostOdds() {
        return nowHostOdds;
    }

    public void setNowHostOdds(String nowHostOdds) {
        this.nowHostOdds = nowHostOdds;
    }

    public String getFirstVisitOdds() {
        return firstVisitOdds;
    }

    public void setFirstVisitOdds(String firstVisitOdds) {
        this.firstVisitOdds = firstVisitOdds;
    }

    public String getNowVisitOdds() {
        return nowVisitOdds;
    }

    public void setNowVisitOdds(String nowVisitOdds) {
        this.nowVisitOdds = nowVisitOdds;
    }

    public String getFirstDrawOdds() {
        return firstDrawOdds;
    }

    public void setFirstDrawOdds(String firstDrawOdds) {
        this.firstDrawOdds = firstDrawOdds;
    }

    public String getNowDrawOdds() {
        return nowDrawOdds;
    }

    public void setNowDrawOdds(String nowDrawOdds) {
        this.nowDrawOdds = nowDrawOdds;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCnName() {
        return companyCnName;
    }

    public void setCompanyCnName(String companyCnName) {
        this.companyCnName = companyCnName;
    }

    public List<Integer> getOddsFloat() {
        return oddsFloat;
    }

    public void setOddsFloat(List<Integer> oddsFloat) {
        this.oddsFloat = oddsFloat;
    }
}
