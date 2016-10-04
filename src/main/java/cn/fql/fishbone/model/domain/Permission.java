package cn.fql.fishbone.model.domain;

import cn.fql.fishbone.model.domain.common.Base;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class Permission extends Base{
    private String permissionname;

    private Long roleId;

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
