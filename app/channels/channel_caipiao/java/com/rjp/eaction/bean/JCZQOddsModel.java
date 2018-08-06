package com.rjp.eaction.bean;

import java.util.List;

/**
 * Created by small on 2018/7/28.
 */

public class JCZQOddsModel {

    /**
     * hostId : 3155
     * visitId : 485
     * visitName : 特鲁瓦
     * leagueName : 法乙
     * matchCode : 周五002
     * hostLogoUrl : http://zx.caipiao.163.com/football/team/3155.html?mid=2792596
     * visitLogoUrl : http://zx.caipiao.163.com/football/team/485.html?mid=2792596
     * matchTime : 02:00
     * hostName : 阿雅克肖
     * matchId : 1397183
     * ouOdds : [{"firstHostOdds":"2.75","nowHostOdds":"2.17","firstVisitOdds":"2.85","nowVisitOdds":"3.33","oddsFloat":[0,0,-1],"firstDrawOdds":"3.22","nowDrawOdds":"3.09","companyId":"1","companyCnName":"99家平均"},{"firstHostOdds":"2.25","nowHostOdds":"2.30","firstVisitOdds":"3.10","nowVisitOdds":"3.30","oddsFloat":[-1,1,1],"firstDrawOdds":"3.10","nowDrawOdds":"2.88","companyId":"42","companyCnName":"威廉希尔"},{"firstHostOdds":"2.20","nowHostOdds":"2.30","firstVisitOdds":"3.30","nowVisitOdds":"3.20","oddsFloat":[0,0,1],"firstDrawOdds":"3.00","nowDrawOdds":"3.00","companyId":"17","companyCnName":"立博"}]
     * dxpOdds : [{"oddsFloat":[0,0,0],"firstBigLevel":null,"firstHandicap":null,"firstSmallLevel":null,"nowBigLevel":null,"nowHandicap":null,"nowSmallLevel":null,"companyId":"229","companyCnName":"澳门彩票"},{"oddsFloat":[1,0,-1],"firstBigLevel":"2.10","firstHandicap":"2/2.5","firstSmallLevel":"1.77","nowBigLevel":"2.10","nowHandicap":"2/2.5","nowSmallLevel":"1.77","companyId":"126","companyCnName":"Bet 365"},{"oddsFloat":[1,0,-1],"firstBigLevel":"2.06","firstHandicap":"2/2.5","firstSmallLevel":"1.80","nowBigLevel":"1.78","nowHandicap":"2","nowSmallLevel":"2.08","companyId":"282","companyCnName":"皇冠"}]
     * asiaOdds : [{"oddsFloat":[-1,0,1],"firstHostLevel":"1.93","nowHostLevel":"1.90","firstVisitLevel":"1.77","nowVisitLevel":"1.80","firstConcede":"平手/半球","nowConcede":"平手/半球","companyId":"229","companyCnName":"澳门彩票"},{"oddsFloat":[-1,0,1],"firstHostLevel":"1.98","nowHostLevel":"1.85","firstVisitLevel":"1.88","nowVisitLevel":"2.00","firstConcede":"平手/半球","nowConcede":"平手/半球","companyId":"126","companyCnName":"Bet 365"},{"oddsFloat":[-1,0,1],"firstHostLevel":"1.95","nowHostLevel":"1.88","firstVisitLevel":"1.93","nowVisitLevel":"2.00","firstConcede":"平手/半球","nowConcede":"平手/半球","companyId":"282","companyCnName":"皇冠"}]
     * ouOddsKaili : []
     * asiaOddsKaili : []
     * dxpOddsKaili : []
     */

    private String hostId;
    private String visitId;
    private String visitName;
    private String leagueName;
    private String matchCode;
    private String hostLogoUrl;
    private String visitLogoUrl;
    private String matchTime;
    private String hostName;
    private String matchId;
    private List<OuOddsBean> ouOdds;
    private List<DxpOddsBean> dxpOdds;
    private List<AsiaOddsBean> asiaOdds;
    private List<?> ouOddsKaili;
    private List<?> asiaOddsKaili;
    private List<?> dxpOddsKaili;

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getHostLogoUrl() {
        return hostLogoUrl;
    }

    public void setHostLogoUrl(String hostLogoUrl) {
        this.hostLogoUrl = hostLogoUrl;
    }

    public String getVisitLogoUrl() {
        return visitLogoUrl;
    }

    public void setVisitLogoUrl(String visitLogoUrl) {
        this.visitLogoUrl = visitLogoUrl;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public List<OuOddsBean> getOuOdds() {
        return ouOdds;
    }

    public void setOuOdds(List<OuOddsBean> ouOdds) {
        this.ouOdds = ouOdds;
    }

    public List<DxpOddsBean> getDxpOdds() {
        return dxpOdds;
    }

    public void setDxpOdds(List<DxpOddsBean> dxpOdds) {
        this.dxpOdds = dxpOdds;
    }

    public List<AsiaOddsBean> getAsiaOdds() {
        return asiaOdds;
    }

    public void setAsiaOdds(List<AsiaOddsBean> asiaOdds) {
        this.asiaOdds = asiaOdds;
    }

    public List<?> getOuOddsKaili() {
        return ouOddsKaili;
    }

    public void setOuOddsKaili(List<?> ouOddsKaili) {
        this.ouOddsKaili = ouOddsKaili;
    }

    public List<?> getAsiaOddsKaili() {
        return asiaOddsKaili;
    }

    public void setAsiaOddsKaili(List<?> asiaOddsKaili) {
        this.asiaOddsKaili = asiaOddsKaili;
    }

    public List<?> getDxpOddsKaili() {
        return dxpOddsKaili;
    }

    public void setDxpOddsKaili(List<?> dxpOddsKaili) {
        this.dxpOddsKaili = dxpOddsKaili;
    }

    public static class OuOddsBean {
        /**
         * firstHostOdds : 2.75
         * nowHostOdds : 2.17
         * firstVisitOdds : 2.85
         * nowVisitOdds : 3.33
         * oddsFloat : [0,0,-1]
         * firstDrawOdds : 3.22
         * nowDrawOdds : 3.09
         * companyId : 1
         * companyCnName : 99家平均
         */

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

    public static class DxpOddsBean {
        /**
         * oddsFloat : [0,0,0]
         * firstBigLevel : null
         * firstHandicap : null
         * firstSmallLevel : null
         * nowBigLevel : null
         * nowHandicap : null
         * nowSmallLevel : null
         * companyId : 229
         * companyCnName : 澳门彩票
         */

        private Object firstBigLevel;
        private Object firstHandicap;
        private Object firstSmallLevel;
        private Object nowBigLevel;
        private Object nowHandicap;
        private Object nowSmallLevel;
        private String companyId;
        private String companyCnName;
        private List<Integer> oddsFloat;

        public Object getFirstBigLevel() {
            return firstBigLevel;
        }

        public void setFirstBigLevel(Object firstBigLevel) {
            this.firstBigLevel = firstBigLevel;
        }

        public Object getFirstHandicap() {
            return firstHandicap;
        }

        public void setFirstHandicap(Object firstHandicap) {
            this.firstHandicap = firstHandicap;
        }

        public Object getFirstSmallLevel() {
            return firstSmallLevel;
        }

        public void setFirstSmallLevel(Object firstSmallLevel) {
            this.firstSmallLevel = firstSmallLevel;
        }

        public Object getNowBigLevel() {
            return nowBigLevel;
        }

        public void setNowBigLevel(Object nowBigLevel) {
            this.nowBigLevel = nowBigLevel;
        }

        public Object getNowHandicap() {
            return nowHandicap;
        }

        public void setNowHandicap(Object nowHandicap) {
            this.nowHandicap = nowHandicap;
        }

        public Object getNowSmallLevel() {
            return nowSmallLevel;
        }

        public void setNowSmallLevel(Object nowSmallLevel) {
            this.nowSmallLevel = nowSmallLevel;
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

    public static class AsiaOddsBean {
        /**
         * oddsFloat : [-1,0,1]
         * firstHostLevel : 1.93
         * nowHostLevel : 1.90
         * firstVisitLevel : 1.77
         * nowVisitLevel : 1.80
         * firstConcede : 平手/半球
         * nowConcede : 平手/半球
         * companyId : 229
         * companyCnName : 澳门彩票
         */

        private String firstHostLevel;
        private String nowHostLevel;
        private String firstVisitLevel;
        private String nowVisitLevel;
        private String firstConcede;
        private String nowConcede;
        private String companyId;
        private String companyCnName;
        private List<Integer> oddsFloat;

        public String getFirstHostLevel() {
            return firstHostLevel;
        }

        public void setFirstHostLevel(String firstHostLevel) {
            this.firstHostLevel = firstHostLevel;
        }

        public String getNowHostLevel() {
            return nowHostLevel;
        }

        public void setNowHostLevel(String nowHostLevel) {
            this.nowHostLevel = nowHostLevel;
        }

        public String getFirstVisitLevel() {
            return firstVisitLevel;
        }

        public void setFirstVisitLevel(String firstVisitLevel) {
            this.firstVisitLevel = firstVisitLevel;
        }

        public String getNowVisitLevel() {
            return nowVisitLevel;
        }

        public void setNowVisitLevel(String nowVisitLevel) {
            this.nowVisitLevel = nowVisitLevel;
        }

        public String getFirstConcede() {
            return firstConcede;
        }

        public void setFirstConcede(String firstConcede) {
            this.firstConcede = firstConcede;
        }

        public String getNowConcede() {
            return nowConcede;
        }

        public void setNowConcede(String nowConcede) {
            this.nowConcede = nowConcede;
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
}
