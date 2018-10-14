package com.chris.lanfeng.web.controller;

import com.chris.lanfeng.data.entity.User;
import com.chris.lanfeng.data.manager.TokenManager;
import com.chris.lanfeng.data.service.UserService;
import com.chris.lanfeng.enums.UserType;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.web.model.ViewResult;
import com.chris.lanfeng.web.model.view.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by xiaoxu on 2016/11/9.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private TokenManager tokenManager;

    @RequestMapping(value = "/user/login")
    public ViewResult autoLogin(@RequestParam String accountValue,
                                @RequestParam String password, HttpServletResponse resp) throws UnsupportedEncodingException {
        ViewResult<UserModel> result = new ViewResult<>();
        resp.addHeader("Access-Control-Allow-Origin", "*");
        Result<User> uRes = userService.userLogin(accountValue, password);
        if (!uRes.isSuccess()) {
            result.addError(uRes.getErrMsg());
            return result;
        }
        User user = uRes.getData();
        //返回token
        Result<String> tRes = tokenManager.generateToken(user.getUserId());
        if (!tRes.isSuccess()) {
            result.addError(tRes.getErrMsg());
            return result;
        }
        UserModel userModel = new UserModel();
        userModel.setUserId(user.getUserId());
        userModel.setNick(user.getNickName());
        userModel.setToken(URLEncoder.encode(tRes.getData(), "UTF-8"));
        userModel.setUserType(UserType.get(user.getUserType()).getDesc());
        result.setObject(userModel);
        return result;
    }


}
