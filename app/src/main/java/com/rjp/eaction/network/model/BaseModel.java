package com.rjp.eaction.network.model;

/**
 *
 * 后台基本数据类型
 * author : Gimpo create on 2018/5/14 10:32
 * email  : jimbo922@163.com
 */
public class BaseModel<T> {
    private int code;
    private int result;
    private String msg;
    private String servertime;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 请求数据成功
     * @return
     */
    public boolean isOk() {
        return code == 1 || result == 100 || code == 100;
    }

    /**
     * 接口需要用户登录
     * @return
     */
    public boolean isRequestLogin(){
        return false;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
