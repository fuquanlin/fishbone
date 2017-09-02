package cn.fql.fishbone.web.dto;

import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public class RoleParam extends PagedQueryParam {
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
