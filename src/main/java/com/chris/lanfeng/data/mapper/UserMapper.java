package com.chris.lanfeng.data.mapper;

import com.chris.lanfeng.data.entity.User;

/**
 * Created by xiaoxu on 2018/1/16.
 */
public interface UserMapper {

    int insert(User user);

    User getByUserId(long userId);

    User getByPhone(long phone);

    User getByNickName(String nickName);
}
