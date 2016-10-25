package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.dao.PermissionDAO;
import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.service.base.PermissionService;
import cn.fql.fishbone.web.dto.PermissionParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/10.
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    PermissionDAO permissionDAO;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionDAO.getAllPermissions();
    }

    @Override
    public List<Permission> queryPermission(PermissionParam permissionParam) {
        return permissionDAO.queryPermission(permissionParam);
    }
}
