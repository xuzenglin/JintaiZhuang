package com.chris.lanfeng.data.entity;

import com.chris.lanfeng.enums.UserType;

import java.util.Date;

/**
 * Created by xiaoxu on 2018/1/16.
 */
public class User {

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private long phone;

    /**
     * 用户类型{@link UserType}
     */
    private int userType;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
