package com.chris.lanfeng.data.service;


import com.chris.lanfeng.data.entity.User;
import com.chris.lanfeng.result.Result;

/**
 * Created by xiaoxu on 2018/1/17.
 */
public interface UserService {


    Result<Void> addUser(String phone, String nickName, String password);

    Result<Void> addUser(User user);

    Result<User> userLogin(String accountValue, String password);

}
