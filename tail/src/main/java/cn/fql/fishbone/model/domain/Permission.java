package cn.fql.fishbone.model.domain;

import cn.fql.fishbone.model.domain.common.Base;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class Permission extends Base{
    private String permissioncode;
    private String permissionname;
    private String category;
    private String type;

    private Long roleId;

    public String getPermissioncode() {
        return permissioncode;
    }

    public void setPermissioncode(String permissioncode) {
        this.permissioncode = permissioncode;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
