package com.chris.lanfeng.data.service;

import com.chris.lanfeng.data.entity.Consult;
import com.chris.lanfeng.result.Result;

import java.util.List;

/**
 * Created by xiaoxu on 2018/5/13.
 */
public interface ConsultService {

    /**
     * 添加咨询
     *
     * @param realName
     * @param telphone
     * @param remark
     * @return
     */
    Result<Void> addConsult(String realName, String telphone, String remark);

    /**
     * 获取未处理信息
     *
     * @return
     */
    Result<List<Consult>> findALL();

    /**
     * 处理
     *
     * @param consultId
     * @return
     */
    Result<Void> update(long consultId);

}
