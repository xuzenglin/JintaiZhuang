package com.chris.lanfeng.data.common;

import java.util.Date;

/**
 * Created by xiaoxu on 2018/3/2.
 */
public class AlarmData {

    /**
     * 报警编号
     */
    private long alarmInfoNo;

    /**
     * 所属单位
     */
    private String deptName = "甘肃蓝枫物流";

    /**
     * 报警类型
     */
    private String alarmType;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 车速
     */
    private double alarmSpeed;


    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 报警附加信息
     */
    private String alarmInfo;

    /**
     * 位置
     */
    private String positionDes;

    public long getAlarmInfoNo() {
        return alarmInfoNo;
    }

    public void setAlarmInfoNo(long alarmInfoNo) {
        this.alarmInfoNo = alarmInfoNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getAlarmSpeed() {
        return alarmSpeed;
    }

    public void setAlarmSpeed(double alarmSpeed) {
        this.alarmSpeed = alarmSpeed;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }

    public String getPositionDes() {
        return positionDes;
    }

    public void setPositionDes(String positionDes) {
        this.positionDes = positionDes;
    }
}
