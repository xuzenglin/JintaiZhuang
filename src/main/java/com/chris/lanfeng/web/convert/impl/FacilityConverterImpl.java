package com.chris.lanfeng.web.convert.impl;

import com.chris.lanfeng.data.entity.Facility;
import com.chris.lanfeng.data.entity.FacilityRecord;
import com.chris.lanfeng.data.service.FacilityService;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.utils.BeanUtil;
import com.chris.lanfeng.utils.Constant;
import com.chris.lanfeng.web.convert.FacilityConverter;
import com.chris.lanfeng.web.model.view.FacilityModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoxu on 2018/3/2.
 */
@Component
public class FacilityConverterImpl implements FacilityConverter {

    @Resource
    private FacilityService facilityService;

    @Override
    public List<FacilityModel> converter(List<Facility> facilities) {
        if (CollectionUtils.isEmpty(facilities)) return null;
        List<FacilityModel> models = new ArrayList<>();
        FacilityModel model = null;
        Result<FacilityRecord> result = null;
        FacilityRecord record = null;
        for (Facility facility : facilities) {
            model = BeanUtil.copyBean(facility, FacilityModel.class);
            result = facilityService.getLastFacilityRecord(facility.getDeviceId());
            if (result.isSuccess() && result.getData() != null) {
                record = result.getData();
                model.setCourse(record.getCourse());
                model.setLastUpdateTime(record.getUploadTime());
                if ((Instant.now().toEpochMilli() - record.getUploadTime()) > 20 * 60 * 1000) {
                    model.setStateDesc("未使用");
                } else {
                    model.setStateDesc("运行中");
                }

            } else {
                model.setStateDesc("未使用");
                model.setLastUpdateTime(Constant.DEFAULT_ID);
            }
            models.add(model);
        }
        return models;
    }
}
