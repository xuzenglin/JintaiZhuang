package com.chris.lanfeng.enums;

/**
 * Created by xiaoxu on 2018/1/16.
 */
public enum UserType {
    USER(0, "用户"),
    ADMIN(1, "管理员"),
    SUPER_ADMIN(2, "超级管理员");

    private int type;

    private String desc;

    UserType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static UserType get(int type) {
        UserType[] values = UserType.values();
        for (UserType value : values) {
            if (value.getType() == type) {
                return value;
            }
        }
        return USER;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
