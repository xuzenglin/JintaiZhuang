package com.chris.lanfeng.web.model.view;

/**
 * 返回给客户端的用户模型
 * Created by Ben on 16/9/5.
 */
public class UserModel {

    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户token
     */
    private String token;
    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 用户类型
     */
    private String userType;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
