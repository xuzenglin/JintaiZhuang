package com.chris.lanfeng.test.service;

import com.chris.lanfeng.data.entity.User;
import com.chris.lanfeng.data.service.UserService;
import com.chris.lanfeng.enums.UserType;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.test.BaseTest;
import com.chris.lanfeng.utils.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by xiaoxu on 2016/11/9.
 */
public class UserServiceTest extends BaseTest {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Resource
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setNickName("admin");
        user.setPassword("7758123");
        user.setPassword(DigestUtils.md5Hex(Constant.APP + "7758123"));
        user.setPhone(13612831910L);
        user.setUserType(UserType.SUPER_ADMIN.getType());
        userService.addUser(user);
    }

    @Test
    public void addUser() {
        userService.addUser("13612831901", "xiaoxu", "7758123");
    }

    @Test
    public void userLogin() {
        Result<User> result = userService.userLogin("xiaoxu", "7758123");
        if (!result.isSuccess()) {
            logger.info("error:{}", result.getErrorMsg());
        } else {
            logger.info("userId:{}", result.getData().getUserId());
        }
    }
}
