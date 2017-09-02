package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.dao.OperationLogDAO;
import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.service.base.OperationLogService;
import cn.fql.fishbone.web.dto.OperationLogParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/3.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    OperationLogDAO operationLogDAO;

    @Override
    public void createOperationLog(OperationLog operationLog) {
        operationLogDAO.insertOperationLog(operationLog);
    }

    @Override
    public List<OperationLog> queryOperationLog(OperationLogParam operationLogParam) {
        return operationLogDAO.queryOperationLog(operationLogParam);
    }
}
