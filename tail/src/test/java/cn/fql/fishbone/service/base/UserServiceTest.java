package cn.fql.fishbone.service.base;

import cn.fql.fishbone.ApplicationMain;
import cn.fql.fishbone.model.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by fuquanlin on 2016/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationMain.class})
@WebAppConfiguration
public class UserServiceTest {
    @Resource
    private UserService userService;


    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("1");
        userService.createUser(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(4L);
    }

    @Test
    public void testGetUserById() {
        User id = userService.getUserById(1L);
        System.out.println(id);
    }
}
