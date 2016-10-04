package cn.fql.fishbone.web.dto;

import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public class RoleParam extends PagedQueryParam {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
