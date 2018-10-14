package com.chris.lanfeng.data.service;

import com.chris.lanfeng.data.common.FacilityListRequest;
import com.chris.lanfeng.data.entity.Facility;
import com.chris.lanfeng.data.entity.FacilityRecord;
import com.chris.lanfeng.result.Result;

import java.util.List;

/**
 * Created by xiaoxu on 2018/1/25.
 */
public interface FacilityService {

    void update();

    /**
     * 拉取数据
     */
    void getData();

    /**
     * 拉去设备数据
     */
    void getDeviceData();

    /**
     * 拉取数据测试
     */
    void getDataTest(String json);

    /**
     * 添加设备
     *
     * @param deviceId
     * @param plateNumber
     * @return
     */
    Result<Void> addFacility(long deviceId, String plateNumber);

    /**
     * 获取所有设备
     *
     * @return
     */
    Result<List<Facility>> findAll();

    /**
     * 刪除设备
     *
     * @param id
     * @return
     */
    Result<Void> deleteFacility(long id);

    /**
     * 获取设备的最后一次记录
     *
     * @param deviceId
     * @return
     */
    Result<FacilityRecord> getLastFacilityRecord(long deviceId);

    /**
     * 添加纪录
     *
     * @param request
     */
    void addFacilityRecord(FacilityListRequest request);

}
