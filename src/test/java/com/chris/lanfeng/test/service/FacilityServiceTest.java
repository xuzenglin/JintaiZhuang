package com.chris.lanfeng.test.service;

import com.chris.lanfeng.data.common.FacilityListRequest;
import com.chris.lanfeng.data.service.FacilityService;
import com.chris.lanfeng.test.BaseTest;
import com.chris.lanfeng.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import static com.chris.lanfeng.utils.JsonUtils.toJsonNoEx;

/**
 * Created by xiaoxu on 2018/3/6.
 */
public class FacilityServiceTest extends BaseTest {
    private final static Logger logger = LoggerFactory.getLogger(FacilityServiceTest.class);
    @Resource
    private FacilityService facilityService;

    @Test
    public void test() {
        try {
            FacilityListRequest listRequest = toJsonNoEx("{\"positions\":[{\"deviceId\":15062,\"accuracy\":0,\"course\":121,\"longitude\":103.66251666666666,\"latitude\":36.0875,\"mode\":0,\"speed\":54,\"time\":1520323372000}]}", FacilityListRequest.class);
            logger.info("listRequest:{}", JsonUtils.toString(listRequest));
            facilityService.addFacilityRecord(listRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDeviceData() {
        facilityService.getDeviceData();
    }

    @Test
    public void getDataTest() {
        facilityService.getDataTest("{\"positions\":[{\"deviceId\":16787,\"accuracy\":0,\"course\":202,\"longitude\":108.84553333333334,\"latitude\":34.2866,\"mode\":0,\"speed\":3,\"time\":1521968953000}]}");
    }


}
