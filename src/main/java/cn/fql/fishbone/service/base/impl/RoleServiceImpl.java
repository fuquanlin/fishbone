package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.dao.RoleDAO;
import cn.fql.fishbone.model.annotation.Module;
import cn.fql.fishbone.model.annotation.Operation;
import cn.fql.fishbone.model.annotation.SelectOne;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.model.enums.OperationLogType;
import cn.fql.fishbone.service.base.RoleService;
import cn.fql.fishbone.web.dto.RoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Module("role")
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    @SelectOne
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    @Operation(value = OperationLogType.CREATE, description = "create role")
    public void insertRole(Role role) {
        roleDAO.insertRole(role);
    }

    @Override
    @Operation(value = OperationLogType.UPDATE, description = "update role")
    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    @Override
    @Operation(value = OperationLogType.DELETE, description = "delete role")
    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    @Override
    public List<Role> queryRole(RoleParam roleParam) {
        return roleDAO.queryRole(roleParam);
    }
}
