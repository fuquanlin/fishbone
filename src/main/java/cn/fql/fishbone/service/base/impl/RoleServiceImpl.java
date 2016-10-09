package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.dao.RoleDAO;
import cn.fql.fishbone.model.annotation.Module;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.service.base.RoleService;
import cn.fql.fishbone.web.dto.RoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Module("role")
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public void insertRole(Role role) {
        roleDAO.insertRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    @Override
    public List<Role> queryRole(RoleParam roleParam) {
        return roleDAO.queryRole(roleParam);
    }
}
