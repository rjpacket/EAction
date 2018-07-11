package com.rjp.eaction.bean;

import java.io.Serializable;

/**
 * author : Gimpo create on 2018/7/11 15:54
 * email  : jimbo922@163.com
 */
public class LoginModel implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
