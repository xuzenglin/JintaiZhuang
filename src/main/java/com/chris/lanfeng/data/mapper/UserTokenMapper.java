package com.chris.lanfeng.data.mapper;

import com.chris.lanfeng.data.entity.UserToken;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiaoxu on 2018/1/16.
 */
public interface UserTokenMapper {

    int insert(UserToken userToken);

    int update(@Param("userId") long userId, @Param("token") String token);

    UserToken getByUserId(long userId);

    int deleteByUserId(long userId);
}
