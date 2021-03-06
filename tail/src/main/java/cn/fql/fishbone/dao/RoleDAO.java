package cn.fql.fishbone.dao;

import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.web.dto.RoleParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface RoleDAO {

    List<Role> getRolesByUserId(Long userId);

    Role getRoleById(Long id);

    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

    List<Role> queryRole(RoleParam RoleParam);
}
