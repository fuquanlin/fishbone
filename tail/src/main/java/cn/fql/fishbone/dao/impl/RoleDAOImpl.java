package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.RoleDAO;
import cn.fql.fishbone.dao.mapper.PermissionMapper;
import cn.fql.fishbone.dao.mapper.RoleMapper;
import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.web.dto.RoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = roleMapper.getRoleById(id);
        role.setPermissions(permissionMapper.getPermissionsByRoleId(id));
        return role;
    }

    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
        List<Permission> permissions = role.getPermissions();
        if (permissions != null) {
            for (Permission permission : permissions) {
                roleMapper.insertRolePermission(role.getId(), permission.getId());
            }
        }
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
        roleMapper.deleteRolePermission(role.getId());
        List<Permission> permissions = role.getPermissions();
        if (permissions != null) {
            for (Permission permission : permissions) {
                roleMapper.insertRolePermission(role.getId(), permission.getId());
            }
        }

    }

    @Override
    public void deleteRole(Long id) {
        roleMapper.deleteRole(id);
        roleMapper.deleteRolePermission(id);
    }

    @Override
    public List<Role> queryRole(RoleParam roleParam) {
        return roleMapper.queryRole(roleParam);
    }
}
