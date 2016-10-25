package cn.fql.fishbone.web.dto;

import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;

/**
 * Created by fuquanlin on 2016/10/25.
 */
public class PermissionParam extends PagedQueryParam {
    private String permissioncode;
    private String permissionname;
    private String category;
    private String type;

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
}
