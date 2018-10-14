package com.chris.lanfeng.data.service.impl;

import com.chris.lanfeng.enums.UserType;
import com.chris.lanfeng.utils.Constant;
import com.chris.lanfeng.data.entity.User;
import com.chris.lanfeng.data.mapper.UserMapper;
import com.chris.lanfeng.data.service.UserService;
import com.chris.lanfeng.result.ErrorCode;
import com.chris.lanfeng.result.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xiaoxu on 2018/1/17.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int PHONE_LENGTH = 11;

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<Void> addUser(String phone, String nickName, String password) {
        Result<Void> result = Result.newResult();
        if (StringUtils.isBlank(phone) || phone.length() != PHONE_LENGTH
                || StringUtils.isBlank(nickName) || StringUtils.isBlank(password)) {
            result.addError(ErrorCode.ARGUMENTS_ERROR);
            return result;
        }
        User user = userMapper.getByPhone(Long.parseLong(phone));
        if (user != null) {
            result.addError(ErrorCode.USER_EXIST);
            return result;
        }
        user = userMapper.getByNickName(nickName);
        if (user != null) {
            result.addError(ErrorCode.USER_EXIST);
            return result;
        }
        user = new User();
        user.setNickName(nickName);
        user.setPassword(DigestUtils.md5Hex(Constant.APP + password));
        user.setPhone(Long.parseLong(phone));
        user.setUserType(UserType.ADMIN.getType());
        int uRes = userMapper.insert(user);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.ADD_FAILED);
        }
        return result;
    }

    @Override
    public Result<Void> addUser(User user) {
        Result<Void> result = Result.newResult();
        int uRes = userMapper.insert(user);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.ADD_FAILED);
        }
        return result;
    }

    @Override
    public Result<User> userLogin(String accountValue, String password) {
        Result<User> result = Result.newResult();
        User user = null;
        try {
            if (accountValue.length() == PHONE_LENGTH) {
                user = userMapper.getByPhone(Long.parseLong(accountValue));
            }
            if (user == null) {
                user = userMapper.getByNickName(accountValue);
            }
            if (user == null) {
                user = userMapper.getByUserId(Long.parseLong(accountValue));
            }
        } catch (Exception e) {
            result.addError(ErrorCode.USER_NOT_EXIST);
            return result;
        }
        if (user == null) {
            result.addError(ErrorCode.USER_NOT_EXIST);
            return result;
        }
        if (!user.getPassword().equals(DigestUtils.md5Hex(Constant.APP + password))) {
            result.addError(ErrorCode.PWD_ERROR);
            return result;
        }
        result.setData(user);
        return result;
    }
}
