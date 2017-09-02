package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.web.dto.UserParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface UserMapper {

    User getUserById(Long id);

    User getUserByName(String name);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> queryUser(UserParam userParam);

    void insertUserRole(Long userId, Long roleId);

    void deleteUserRoleByUserId(Long userId);



}
