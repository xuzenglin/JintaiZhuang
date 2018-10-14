package com.chris.lanfeng.data.common;

import java.util.List;

/**
 * Created by xiaoxu on 2018/3/28.
 */
public class DeviceDataRequest {

    private int code;

    private String msg;

    private List<DeviceData> data;

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

    public List<DeviceData> getData() {
        return data;
    }

    public void setData(List<DeviceData> data) {
        this.data = data;
    }
}
