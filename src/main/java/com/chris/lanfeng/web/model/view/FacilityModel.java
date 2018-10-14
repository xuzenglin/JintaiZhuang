package com.chris.lanfeng.web.model.view;

/**
 * Created by xiaoxu on 2018/3/2.
 */
public class FacilityModel {

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

    /**
     * 最后更新时间
     */
    private long lastUpdateTime;

    /**
     * 里程
     */
    private int course;

    /**
     * 目前状态
     */
    private String stateDesc;

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

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }
}
