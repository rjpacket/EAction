package com.rjp.eaction.utils;

/**
 * Created by small on 2018/7/24.
 */

public class LotteryType {
    public static String getLotteryName(String type) {
        switch (type) {
            case "football_9":
                return "足彩任选九";
            case "qxc":
                return "七星彩";
            case "ssc":
                return "时时彩";
            case "hbkuai3":
                return "湖北快三";
            case "d11":
                return "11选5";
            case "jxssc":
                return "江西时时彩";
            case "jxd11":
                return "江西11选5";
            case "feiyu":
                return "乌龙球";
            case "football_sfc":
                return "足彩胜负彩";
            case "hljd11":
                return "欢乐11选5";
            case "gxkuai3":
                return "广西快三";
            case "lnd11":
                return "辽宁11选5";
            case "ssq":
                return "双色球";
            case "gdd11":
                return "广东11选5";
            case "dlt":
                return "大乐透";
            case "zjd11":
                return "浙江11选5";
            case "x3d":
                return "福彩3D";
            case "pl5":
                return "排列五";
            case "qlc":
                return "七乐彩";
            case "kuai3":
                return "快三";
            case "klpk":
                return "快乐扑克";
            case "pl3":
                return "排列三";
            case "oldkuai3":
                return "老快三";
        }
        return "未知";
    }
}
