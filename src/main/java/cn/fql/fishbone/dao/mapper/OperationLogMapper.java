package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.web.dto.OperationLogParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/9/30.
 */
public interface OperationLogMapper {

    void insertOperationLog(OperationLog operationLog);

    List<OperationLog> queryOperationLog(OperationLogParam operationLogParam);
}
