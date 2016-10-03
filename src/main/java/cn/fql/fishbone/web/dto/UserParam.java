package cn.fql.fishbone.web.dto;

import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public class UserParam extends PagedQueryParam {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
