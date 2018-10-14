package com.chris.lanfeng.data.common;

import java.util.Date;

/**
 * Created by xiaoxu on 2018/2/28.
 */
public class FacilityLocation {

    /**
     * 所属单位
     */
    private String deptName="甘肃蓝枫物流";

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 位置
     */
    private String positionDes;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 方向
     */
    private String direction;

    /**
     * 里程
     */
    private double mileage;

    /**
     * 车速
     */
    private double speed;

    /**
     * 定位时间
     */
    private Date positioningTime;

    /**
     * 车辆状态
     */
    private String status;

    /**
     * ACC状态
     */
    private int addstatus;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getPositionDes() {
        return positionDes;
    }

    public void setPositionDes(String positionDes) {
        this.positionDes = positionDes;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Date getPositioningTime() {
        return positioningTime;
    }

    public void setPositioningTime(Date positioningTime) {
        this.positioningTime = positioningTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(int addstatus) {
        this.addstatus = addstatus;
    }
}


