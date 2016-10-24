package cn.fql.fishbone.model.domain;

import cn.fql.fishbone.model.domain.common.Base;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class User extends Base {
    private String username;
    private String password;
    private String email;

    private List<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
