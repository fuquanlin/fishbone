package cn.fql.fishbone.controller;

import cn.fql.fishbone.ApplicationMain;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fuquanlin on 2016/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationMain.class})
@WebAppConfiguration
public class RoleControllerTest {
}
