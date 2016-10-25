package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.service.base.PermissionService;
import cn.fql.fishbone.util.ResultBuilder;
import cn.fql.fishbone.web.dto.OperationLogParam;
import cn.fql.fishbone.web.dto.PermissionParam;
import cn.fql.fishbone.web.dto.pagination.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/25.
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<PageResult<Permission>> queryPermission(PermissionParam permissionParam) {
        List<Permission> permissions = permissionService.queryPermission(permissionParam);
        return ResultBuilder.build(permissionParam,permissions);
    }
}
