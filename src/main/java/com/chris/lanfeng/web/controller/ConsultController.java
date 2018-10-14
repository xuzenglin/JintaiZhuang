package com.chris.lanfeng.web.controller;

import com.chris.lanfeng.data.entity.Consult;
import com.chris.lanfeng.data.service.ConsultService;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.web.model.ViewResult;
import com.langu.authorization.annotation.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiaoxu on 2018/5/13.
 */
@RestController
@RequestMapping("/api/consult")
public class ConsultController {

    @Resource
    private ConsultService consultService;

    @RequestMapping(value = "/add")
    public ViewResult addFacility(@RequestParam String realName,
                                  @RequestParam String telphone,
                                  @RequestParam String remark,
                                  HttpServletResponse resp) {
        ViewResult result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<Void> uRes = consultService.addConsult(realName, telphone, remark);
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/list")
    @Auth
    public ViewResult findALL(HttpServletResponse resp) {
        ViewResult result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<List<Consult>> uRes = consultService.findALL();
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        result.setObject(uRes.getData());
        return result;
    }

    @RequestMapping(value = "/update")
    @Auth
    public ViewResult update(@RequestParam Long consultId,
                             HttpServletResponse resp) {
        ViewResult result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<Void> uRes = consultService.update(consultId);
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        result.setObject(uRes.getData());
        return result;
    }

}
