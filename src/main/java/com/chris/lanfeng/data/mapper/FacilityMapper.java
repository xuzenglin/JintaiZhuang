package com.chris.lanfeng.data.mapper;


import com.chris.lanfeng.data.entity.Facility;

import java.util.List;

/**
 * Created by xiaoxu on 2018/1/25.
 */
public interface FacilityMapper {

    int insert(Facility facility);

    Facility getByDeviceId(long deviceId);

    Facility getByPlateNumber(String plateNumber);

    List<Facility> findAll();

    int delete(Long id);

}
