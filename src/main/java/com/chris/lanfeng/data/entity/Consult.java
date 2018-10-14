package com.chris.lanfeng.data.entity;

/**
 * Created by xiaoxu on 2018/5/13.
 */
public class Consult {

    /**
     * ID
     */
    private long id;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 联系方式
     */
    private String telphone;

    /**
     * 状态:0.待处理 1.已处理 2.删除
     */
    private int state;

    /**
     * 描述
     */
    private String remark;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
