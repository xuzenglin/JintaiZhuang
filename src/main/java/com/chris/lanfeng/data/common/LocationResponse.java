package com.chris.lanfeng.data.common;

/**
 * Created by xiaoxu on 2018/2/28.
 */
public class LocationResponse {

    private int source = 3;

    /**
     * 传输时间
     */
    private long time;

    /**
     * 报警来源系统
     */
    private int sysId = 10008;

    /**
     * 传输数据
     */
    private Object data;

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
