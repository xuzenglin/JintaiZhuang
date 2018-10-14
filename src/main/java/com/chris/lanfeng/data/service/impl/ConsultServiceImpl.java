package com.chris.lanfeng.data.service.impl;

import com.chris.lanfeng.data.entity.Consult;
import com.chris.lanfeng.data.mapper.ConsultMapper;
import com.chris.lanfeng.data.service.ConsultService;
import com.chris.lanfeng.result.ErrorCode;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.utils.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

/**
 * Created by xiaoxu on 2018/5/13.
 */
@Service
public class ConsultServiceImpl implements ConsultService {
    private final static Logger logger = LoggerFactory.getLogger(ConsultServiceImpl.class);

    @Resource
    private ConsultMapper consultMapper;

    @Override
    public Result<Void> addConsult(String realName, String telphone, String remark) {
        Result<Void> result = Result.newResult();
        if (StringUtils.isBlank(realName) || StringUtils.isBlank(telphone) || StringUtils.isBlank(remark)) {
            result.addError(ErrorCode.ARGUMENTS_ERROR);
            return result;
        }
        Consult consult = new Consult();
        consult.setRealName(realName);
        consult.setRemark(remark);
        consult.setTelphone(telphone);
        consult.setCreateTime(Instant.now().toEpochMilli());
        int uRes = consultMapper.insert(consult);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.ADD_FAILED);
            return result;
        }
        return result;
    }

    @Override
    public Result<List<Consult>> findALL() {
        Result<List<Consult>> result = Result.newResult();
        List<Consult> consults = consultMapper.findAll();
        if (CollectionUtils.isNotEmpty(consults)) {
            result.setData(consults);
        }
        return result;
    }

    @Override
    public Result<Void> update(long consultId) {
        Result<Void> result = Result.newResult();
        int uRes = consultMapper.update(consultId);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.UPDATE_FAILED);
            return result;
        }
        return result;
    }
}
