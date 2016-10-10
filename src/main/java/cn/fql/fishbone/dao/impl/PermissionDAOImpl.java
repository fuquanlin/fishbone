package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.PermissionDAO;
import cn.fql.fishbone.dao.mapper.PermissionMapper;
import cn.fql.fishbone.model.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Repository
public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }

    @Override
    public List<Permission> getPermissionsByRoleId(Long id) {
        return permissionMapper.getPermissionsByRoleId(id);
    }

    @Override
    public void insertPermission(Permission permission) {
        permissionMapper.insertPermission(permission);
    }

    @Override
    public void deletePermissionByRoleId(Integer id) {
        permissionMapper.deletePermissionByRoleId(id);
    }
}
