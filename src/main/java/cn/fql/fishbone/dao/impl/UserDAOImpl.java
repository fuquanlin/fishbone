package cn.fql.fishbone.dao.impl;

import cn.fql.fishbone.dao.UserDAO;
import cn.fql.fishbone.dao.mapper.UserMapper;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.model.domain.User;
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

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }


}
