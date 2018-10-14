package com.chris.lanfeng.result;

/**
 * Created by xiaoxu on 2017/8/3.
 */
public enum ErrorCode {
    LOGIN_TOKEN_INVALID(10001, "您的身份信息异常，请重新登录"),
    ARGUMENTS_NULL(10002, "参数不能为空"),
    ARGUMENTS_ERROR(10003, "参数错误"),
    USER_NOT_EXIST(10004, "用户不存在"),
    USER_EXIST(10005, "用户已存在"),
    INVALID_REQUEST(10006, "非法请求"),
    UPDATE_FAILED(10007, "更新失败"),
    ADD_FAILED(10008, "添加失败"),
    LOGIN_AGR_INVALID(10009, "登录参数异常"),
    SYSTEM_REQUEST_FREQUENT(10010, "请求过于频繁"),
    PWD_ERROR(10011, "密码错误"),
    DEVICE_EXIST(10012, "设备已绑定"),
    PLATE_NUMBER_EXIST(10013, "该车牌已绑定"),


    TOKEN_UPDATE_FAILED(20000, "token更新失败"),
    TOKEN_INSERT_FAILED(20001, "token插入失败"),
    TOKEN_DELETE_FAILED(20002, "token删除失败"),
    TOKEN_GENERATE_FAILED(20003, "token生成失败"),


    SYSTEM_ERROR(10000, "当前系统错误，请稍后再试");

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode get(int code) {
        ErrorCode[] values = ErrorCode.values();
        for (ErrorCode value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
