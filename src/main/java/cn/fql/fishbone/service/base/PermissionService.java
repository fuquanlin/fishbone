package cn.fql.fishbone.service.base;

import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.web.dto.PermissionParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/10.
 */
public interface PermissionService {

    List<Permission> queryPermission(PermissionParam permissionParam);

    List<Permission> getAllPermissions();
}
