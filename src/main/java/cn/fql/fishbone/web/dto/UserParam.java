package cn.fql.fishbone.web.dto;

import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public class UserParam extends PagedQueryParam {
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
