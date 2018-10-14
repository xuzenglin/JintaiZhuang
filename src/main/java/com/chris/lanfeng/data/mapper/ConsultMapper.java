package com.chris.lanfeng.data.mapper;

import com.chris.lanfeng.data.entity.Consult;

import java.util.List;

/**
 * Created by xiaoxu on 2018/5/13.
 */
public interface ConsultMapper {

    int insert(Consult consult);

    List<Consult> findAll();

    int delete(Long id);

    int update(Long id);
}
