package com.rjp.eaction.network.model;

/**
 * author : Gimpo create on 2018/5/22 15:26
 * email  : jimbo922@163.com
 */
public class Code {
    public static final int NET_ERROR = 90001;

    public static String getErrorMsg(int code) {
        switch (code){
            case NET_ERROR:
                return "网络异常";
        }
        return null;
    }
}
