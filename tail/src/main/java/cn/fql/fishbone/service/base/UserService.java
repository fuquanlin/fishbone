package cn.fql.fishbone.service.base;

import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.web.dto.UserParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public interface UserService {

    User getUserById(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> queryUser(UserParam userParam);
}
