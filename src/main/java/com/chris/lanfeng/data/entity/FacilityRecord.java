package com.chris.lanfeng.data.entity;

/**
 * Created by xiaoxu on 2018/2/7.
 */
public class FacilityRecord {

    /**
     * 记录ID
     */
    private long id;

    /**
     * 设备ID
     */
    private long deviceId;

    /**
     * 定位的精度
     */
    private int accuracy;

    /**
     * 里程
     */
    private int course;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * mode是定位模式  0是gps 1是基站  2是wifi
     * mode！=0  accuracy >0
     */
    private int mode;

    /**
     * 是否上传 0.未上传 1.已上传
     */
    private int uploadCode;

    /**
     * 速度
     */
    private int speed;

    /**
     * 上传时间
     */
    private long uploadTime;

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

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getUploadCode() {
        return uploadCode;
    }

    public void setUploadCode(int uploadCode) {
        this.uploadCode = uploadCode;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }
}
