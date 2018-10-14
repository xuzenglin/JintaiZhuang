package com.chris.lanfeng.data.common;

import java.util.List;

/**
 * Created by xiaoxu on 2018/3/25.
 */
public class DeviceListRequest {

    /**
     * 数据模型
     */
    List<DeviceRequest> devices;

    public List<DeviceRequest> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceRequest> devices) {
        this.devices = devices;
    }
}
