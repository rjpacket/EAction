package com.rjp.eaction.bean;

public class UserLoginModel {

    /**
     * token : ca16bcaa-ffa1-4a62-893a-2612239abc8d
     * user : {"id":"4","userName":"admin","userPassword":"","userWechat":"","userWechatStatus":"","userMobile":"","userPortrait":"","userCreated":"","userUpdated":"","rabbitId":"","rabbitType":"","rabbitOnline":"","accountStatus":"","roleId":"","headPortrait":""}
     */

    private String token;
    private UserModel user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public static class UserModel {
    }
}
