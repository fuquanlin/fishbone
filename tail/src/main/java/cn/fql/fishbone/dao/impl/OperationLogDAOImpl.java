package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.OperationLogDAO;
import cn.fql.fishbone.dao.mapper.OperationLogMapper;
import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.web.dto.OperationLogParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/3.
 */
@Repository
public class OperationLogDAOImpl implements OperationLogDAO {

    @Autowired
    OperationLogMapper operationLogMapper;


    @Override
    public void insertOperationLog(OperationLog operationLog) {
        operationLogMapper.insertOperationLog(operationLog);
    }

    @Override
    public List<OperationLog> queryOperationLog(OperationLogParam operationLogParam) {
        return operationLogMapper.queryOperationLog(operationLogParam);
    }
}
