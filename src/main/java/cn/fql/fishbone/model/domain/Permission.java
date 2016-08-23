package cn.fql.fishbone.model.domain;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class Permission {
    private Integer id;
    private String permissionname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }
}
