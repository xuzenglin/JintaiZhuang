package com.chris.lanfeng.data.mapper;

import com.chris.lanfeng.data.entity.FacilityRecord;

/**
 * Created by xiaoxu on 2018/2/8.
 */
public interface FacilityRecordMapper {

    int insert(FacilityRecord record);

    int updateStatus(long deviceId, int status);

    FacilityRecord getByDeviceId(long deviceId);

    FacilityRecord getLastFacilityRecordByDeviceId(long deviceId);

}
