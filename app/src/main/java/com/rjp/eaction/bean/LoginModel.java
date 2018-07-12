package com.rjp.eaction.bean;

import java.io.Serializable;

/**
 * author : Gimpo create on 2018/7/11 15:54
 * email  : jimbo922@163.com
 */
public class LoginModel implements Serializable {
    private String token;
    private String name;
    private String sex;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
