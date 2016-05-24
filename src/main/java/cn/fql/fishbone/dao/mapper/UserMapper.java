package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.User;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface UserMapper {
    User getUserByName(String name);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
