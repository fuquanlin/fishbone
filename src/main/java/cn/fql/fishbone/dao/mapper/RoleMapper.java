package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.web.dto.RoleParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface RoleMapper {
    List<Role> getUserRolesById(Long id);

    Role getRoleById(Long id);

    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

    List<Role> queryRole(RoleParam RoleParam);

    List<Role> getRolesByUserId(Long userId);
}
