package com.chris.lanfeng.web.controller;

import com.chris.lanfeng.data.entity.Facility;
import com.chris.lanfeng.data.service.FacilityService;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.web.convert.FacilityConverter;
import com.chris.lanfeng.web.model.ViewResult;
import com.langu.authorization.annotation.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiaoxu on 2018/3/2.
 */
@RestController
@RequestMapping("/api")
public class FacilityController {
    @Resource
    private FacilityService facilityService;
    @Resource
    private FacilityConverter facilityConverter;

    @RequestMapping(value = "/facility/add")
    @Auth
    public ViewResult addFacility(@RequestParam long deviceId,
                                  @RequestParam String plateNumber,
                                  HttpServletResponse resp) {
        ViewResult result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<Void> uRes = facilityService.addFacility(deviceId, plateNumber);
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/facility/delete")
    @Auth
    public ViewResult deleteFacility(@RequestParam long id,
                                     HttpServletResponse resp) {
        ViewResult result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<Void> uRes = facilityService.deleteFacility(id);
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/facility/list")
    @Auth
    public ViewResult findAll(HttpServletResponse resp) {
        ViewResult result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<List<Facility>> uRes = facilityService.findAll();
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        result.setObject(facilityConverter.converter(uRes.getData()));
        return result;
    }
}
