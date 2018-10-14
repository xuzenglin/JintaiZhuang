package com.chris.lanfeng.data.entity;

/**
 * Created by xiaoxu on 2018/1/25.
 */
public class Facility {

    /**
     * 设备记录ID
     */
    private long id;

    /**
     * 设备码
     */
    private long deviceId;

    /**
     * 车牌号码
     */
    private String plateNumber;

    /**
     * 创建时间
     */
    private long createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
