package com.chris.lanfeng.web.convert;

import com.chris.lanfeng.data.entity.Facility;
import com.chris.lanfeng.web.model.view.FacilityModel;

import java.util.List;

/**
 * Created by xiaoxu on 2018/3/2.
 */
public interface FacilityConverter {

    List<FacilityModel> converter(List<Facility> facilities);
}
