package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.service.base.OperationLogService;
import cn.fql.fishbone.util.ResultBuilder;
import cn.fql.fishbone.web.dto.OperationLogParam;
import cn.fql.fishbone.web.dto.pagination.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/9.
 */
@RestController
@RequestMapping("/log")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<PageResult<OperationLog>> queryOperationLog(OperationLogParam operationLogParam) {
        List<OperationLog> operationLogs = operationLogService.queryOperationLog(operationLogParam);
        return ResultBuilder.build(operationLogParam,operationLogs);
    }
}
