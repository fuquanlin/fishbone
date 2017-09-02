package cn.fql.fishbone.dao;

import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.web.dto.OperationLogParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public interface OperationLogDAO {

    void insertOperationLog(OperationLog operationLog);

    List<OperationLog> queryOperationLog(OperationLogParam operationLogParam);
}
