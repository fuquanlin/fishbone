package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.RoleDAO;
import cn.fql.fishbone.dao.mapper.RoleMapper;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.web.dto.RoleParam;
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
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleMapper.deleteRole(id);
    }

    @Override
    public List<Role> queryRole(RoleParam roleParam) {
        return roleMapper.queryRole(roleParam);
    }
}
