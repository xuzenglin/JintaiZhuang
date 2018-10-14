package com.chris.lanfeng.data.manager;

import com.chris.lanfeng.result.Result;

/**
 * Created by xiaoxu on 2018/1/17.
 */
public interface TokenManager {

    /**
     * 获取token
     *
     * @param userId 用户ID
     * @return
     */
    Result<String> generateToken(long userId);

    /**
     * 删除用户token
     *
     * @param userId
     * @return
     */
    Result<Void> removeToken(long userId);


    /**
     * 获取用户token
     *
     * @param userId
     * @return
     */
    Result<String> getUserToken(long userId);


}
