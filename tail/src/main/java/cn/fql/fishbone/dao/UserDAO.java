package cn.fql.fishbone.dao;

import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.web.dto.UserParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface UserDAO {

    User getUserById(Long id);

    User getUserByName(String name);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    public List<User> queryUser(UserParam userParam);
}
