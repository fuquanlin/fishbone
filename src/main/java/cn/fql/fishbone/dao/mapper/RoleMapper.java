package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.Role;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface RoleMapper {
    List<Role> getUserRolesById(Long id);
}
