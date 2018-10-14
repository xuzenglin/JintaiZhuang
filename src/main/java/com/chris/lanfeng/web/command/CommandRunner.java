package com.chris.lanfeng.web.command;

import com.chris.lanfeng.data.service.FacilityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xiaoxu on 2018/2/6.
 */
@Component
public class CommandRunner implements CommandLineRunner {

    @Resource
    private FacilityService facilityService;

    @Override
    public void run(String... args) throws Exception {
        facilityService.getData();
    }
}
