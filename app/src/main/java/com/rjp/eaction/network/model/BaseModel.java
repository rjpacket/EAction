package com.rjp.eaction.network.model;

/**
 *
 * 后台基本数据类型
 * author : Gimpo create on 2018/5/14 10:32
 * email  : jimbo922@163.com
 */
public class BaseModel {
    private int code;
    private String msg;
    private String servertime;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 请求数据成功
     * @return
     */
    public boolean isOk() {
        return code == 1;
    }

    /**
     * 接口需要用户登录
     * @return
     */
    public boolean isRequestLogin(){
        return false;
    }
}
