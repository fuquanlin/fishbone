package cn.fql.fishbone.service.base;

import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.web.dto.OperationLogParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public interface OperationLogService {

    /**
     * 创建操作动作日志
     *
     * @param operationLog
     */
    void createOperationLog(OperationLog operationLog);

    /**
     * 查询操作日志
     *
     * @param operationLogParam
     * @return
     */
    List<OperationLog> queryOperationLog(OperationLogParam operationLogParam);
}
