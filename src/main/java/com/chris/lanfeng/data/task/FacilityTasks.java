package com.chris.lanfeng.data.task;

import com.chris.lanfeng.data.service.FacilityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xiaoxu on 2018/4/18.
 */
@Component
public class FacilityTasks {
    @Resource
    private FacilityService facilityService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    private void schedule() {
//        facilityService.update();
    }

}
