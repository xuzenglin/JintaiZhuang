package com.chris.lanfeng.data.common;

/**
 * Created by xiaoxu on 2018/3/25.
 */
public class DeviceRequest {

    /**
     * 设备ID
     */
    private long id;

    /**
     * 最后更新时间
     */
    private long lastUpdate;

    /**
     * 状态 1.上线 0.下线
     */
    private int status;

    private String imei;

    private Object attributes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
