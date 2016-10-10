package cn.fql.fishbone.service.base;

import cn.fql.fishbone.model.domain.Permission;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/10.
 */
public interface PermissionService {

    List<Permission> getAllPermissions();
}
