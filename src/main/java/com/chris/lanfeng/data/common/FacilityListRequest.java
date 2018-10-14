package com.chris.lanfeng.data.common;


import java.util.List;

/**
 * Created by xiaoxu on 2018/2/6.
 */
public class FacilityListRequest {

    /**
     * 数据模型
     */
    List<FacilityData> positions;

    public FacilityListRequest() {
    }

    public FacilityListRequest(List<FacilityData> positions) {
        this.positions = positions;
    }

    public List<FacilityData> getPositions() {
        return positions;
    }

    public void setPositions(List<FacilityData> positions) {
        this.positions = positions;
    }
}
