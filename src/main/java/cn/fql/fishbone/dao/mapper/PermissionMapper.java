package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.Permission;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface PermissionMapper {
    List<Permission> getPermissionsByRoleId(Long id);
}
