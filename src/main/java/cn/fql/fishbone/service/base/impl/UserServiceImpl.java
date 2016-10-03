package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.dao.UserDAO;
import cn.fql.fishbone.model.annotation.Module;
import cn.fql.fishbone.model.annotation.Operation;
import cn.fql.fishbone.model.annotation.SelectOne;
import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.model.enums.OperationLogType;
import cn.fql.fishbone.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Module("user")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    @SelectOne
    public User getUserById(Long id) {
        //:todo
        return null;
    }

    @Override
    @Operation(value = OperationLogType.CREATE, description = "create user")
    public void createUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    @Operation(value = OperationLogType.UPDATE, description = "update user")
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Operation(value = OperationLogType.DELETE, description = "delete user")
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);
    }
}
