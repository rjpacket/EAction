package com.rjp.eaction.utils;

/**
 * Created by small on 2018/7/24.
 */

public class EventType {
    public static String getTypeName(String type) {
        switch (type) {
            case "1":
                return "进球";
            case "2":
                return "黄牌";
            case "3":
                return "";
            case "4":
                return "";
            case "5":
                return "换出";
            case "6":
                return "换入";
            case "7":
                return "红牌";
            case "12":
                return "乌龙球";
            case "23":
                return "点球";
        }
        return "未知";
    }
}
