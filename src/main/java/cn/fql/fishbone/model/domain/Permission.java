package cn.fql.fishbone.model.domain;

import cn.fql.fishbone.model.domain.common.Base;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class Permission extends Base{
    private String permissionname;

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }
}
