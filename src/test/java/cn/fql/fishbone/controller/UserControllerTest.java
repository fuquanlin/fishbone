package cn.fql.fishbone.controller;

import cn.fql.fishbone.ApplicationMain;
import cn.fql.fishbone.service.base.UserService;
import cn.fql.fishbone.web.controller.AuthenticationController;
import cn.fql.fishbone.web.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by fuquanlin on 2016/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationMain.class})
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;

//    @Mock
//    private UserService userService;

//    @InjectMocks
//    UserController userController;

    @Autowired
    UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testQueryUser() throws Exception{
        mockMvc.perform(get("/user/list")).andDo(print());
    }
}
