package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.UserDAO;
import cn.fql.fishbone.dao.mapper.RoleMapper;
import cn.fql.fishbone.dao.mapper.UserMapper;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.web.dto.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        user.setRoles(roleMapper.getRolesByUserId(id));
        return user;
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
        List<Role> roles = user.getRoles();
        if (roles!=null){
            for (Role role:roles){
                userMapper.insertUserRole(user.getId(),role.getId());
            }
        }
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
        userMapper.deleteUserRoleByUserId(user.getId());
        List<Role> roles = user.getRoles();
        if (roles!=null){
            for (Role role:roles){
                userMapper.insertUserRole(user.getId(),role.getId());
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
        userMapper.deleteUserRoleByUserId(id);
    }

    @Override
    public List<User> queryUser(UserParam userParam) {
        return userMapper.queryUser(userParam);
    }
}
