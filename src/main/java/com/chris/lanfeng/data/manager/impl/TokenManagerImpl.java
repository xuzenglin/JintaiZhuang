package com.chris.lanfeng.data.manager.impl;

import com.chris.lanfeng.data.entity.UserToken;
import com.chris.lanfeng.data.manager.TokenManager;
import com.chris.lanfeng.data.mapper.UserTokenMapper;
import com.chris.lanfeng.result.ErrorCode;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.utils.Constant;
import com.langu.authorization.token.BlowfishToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xiaoxu on 2018/1/17.
 */
@Component
public class TokenManagerImpl implements TokenManager {
    private final static Logger logger = LoggerFactory.getLogger(TokenManagerImpl.class);


    @Resource
    private UserTokenMapper userTokenMapper;


    @Override
    public Result<String> generateToken(long userId) {
        Result<String> result = Result.newResult();
        BlowfishToken token = new BlowfishToken();
        token.setUserId(userId);
        token.setTokenTime(System.currentTimeMillis());
        try {
            String str = token.generate();
            UserToken userToken = userTokenMapper.getByUserId(userId);
            if (userToken != null) {
                userTokenMapper.update(userId, str);
            } else {
                userToken = new UserToken();
                userToken.setToken(str);
                userToken.setUserId(userId);
                userTokenMapper.insert(userToken);
            }
            result.setData(str);
        } catch (Exception e) {
            result.addError(ErrorCode.TOKEN_GENERATE_FAILED);
            logger.error("generate token exception", e);
        }
        return result;
    }

    @Override
    public Result<Void> removeToken(long userId) {
        Result<Void> result = Result.newResult();
        int uRes = userTokenMapper.deleteByUserId(userId);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.UPDATE_FAILED);
            return result;
        }
        return result;
    }

    @Override
    public Result<String> getUserToken(long userId) {
        Result<String> result = Result.newResult();
        UserToken token = userTokenMapper.getByUserId(userId);
        if (token != null) {
            result.setData(token.getToken());
        }
        return result;
    }
}
