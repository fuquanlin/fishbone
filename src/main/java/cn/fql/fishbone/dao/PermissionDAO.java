package cn.fql.fishbone.dao;

import cn.fql.fishbone.model.domain.Permission;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface PermissionDAO {
    List<Permission> getPermissionsByRoleId(Integer id);
}
