package com.chris.lanfeng.data.entity;

import java.util.Date;

/**
 * Created by xiaoxu on 2018/1/16.
 */
public class UserToken {
    /**
     * 用户ID
     */
    private long userId;

    /**
     * token字符串
     */
    private String token;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
