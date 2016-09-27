package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.RoleDAO;
import cn.fql.fishbone.dao.mapper.RoleMapper;
import cn.fql.fishbone.model.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getUserRolesById(Long id) {
        return roleMapper.getUserRolesById(id);
    }
}
