package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.service.base.RoleService;
import cn.fql.fishbone.util.ResultBuilder;
import cn.fql.fishbone.web.dto.RoleParam;
import cn.fql.fishbone.web.dto.pagination.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fuquanlin on 2016/9/29.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<PageResult<Role>> queryRole(RoleParam roleParam) {
        List<Role> roles = roleService.queryRole(roleParam);
        return ResultBuilder.build(roleParam,roles);
    }

    @RequestMapping(value = "/{roleId}", method = {RequestMethod.GET})
    public Result<Role> getRoleById(@PathVariable("roleId") Long id){
        Role role = roleService.getRoleById(id);
        return ResultBuilder.build(role);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<Void> createRole(@RequestBody Role role){
        roleService.insertRole(role);
        return ResultBuilder.success();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<Void> updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return ResultBuilder.success();
    }

    @RequestMapping(value = "{roleId}", method = RequestMethod.DELETE)
    public Result<Void> deleteRole(@PathVariable("roleId") Long roleId){
        roleService.deleteRole(roleId);
        return ResultBuilder.success();
    }


}
