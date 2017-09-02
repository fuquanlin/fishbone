package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.FishBoneConstants;
import cn.fql.fishbone.dao.UserDAO;
import cn.fql.fishbone.model.annotation.Module;
import cn.fql.fishbone.model.annotation.Operation;
import cn.fql.fishbone.model.annotation.SelectOne;
import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.model.enums.OperationLogType;
import cn.fql.fishbone.service.base.UserService;
import cn.fql.fishbone.web.dto.UserParam;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Module("user")
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;


    @Override
    @SelectOne
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    @Operation(value = OperationLogType.CREATE, description = "create user")
    public void createUser(User user) {
        String sha1 = new Sha256Hash(user.getPassword(), FishBoneConstants.PASSWORD_SALT).toString();
        user.setPassword(sha1);
        userDAO.insertUser(user);

    }

    @Override
    @Transactional
    @Operation(value = OperationLogType.UPDATE, description = "update user")
    public void updateUser(User user) {
        if (user.getPassword() != null) {
            String sha1 = new Sha256Hash(user.getPassword(), FishBoneConstants.PASSWORD_SALT).toString();
            user.setPassword(sha1);
        }
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    @Operation(value = OperationLogType.DELETE, description = "delete user")
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @HystrixCommand(fallbackMethod = "queryFallback")
    public List<User> queryUser(UserParam userParam) {
        return userDAO.queryUser(userParam);
    }

    public List<User> queryFallback(UserParam userParam){
        logger.info("queryUser fallback");
        return null;
    }
}
