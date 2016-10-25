package cn.fql.fishbone.dao;

import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.web.dto.PermissionParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface PermissionDAO {

    List<Permission> queryPermission(PermissionParam permissionParam);

    List<Permission> getAllPermissions();

    List<Permission> getPermissionsByRoleId(Long id);

    void insertPermission(Permission permission);

    void deletePermissionByRoleId(Integer id);
}
