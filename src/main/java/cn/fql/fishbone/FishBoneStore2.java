package cn.fql.fishbone;

import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.service.base.PermissionService;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/10.
 */
public class FishBoneStore2 {
    public static List<Permission> ALL_PERMSSIONS = null;

    private PermissionService permissionService;

    public FishBoneStore2(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    void init() {
        ALL_PERMSSIONS = permissionService.getAllPermissions();
    }

}
