package cn.fql.fishbone.model.domain;

import cn.fql.fishbone.model.domain.common.Base;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class Role extends Base{
    private String rolename;
    private List<Permission> permissions;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
