package cn.fql.fishbone.service.base;

import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.web.dto.RoleParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface RoleService {
    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

    List<Role> queryRole(RoleParam roleParam);
}
