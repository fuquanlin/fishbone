package cn.fql.fishbone.controller;

import cn.fql.fishbone.ApplicationMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuquanlin on 2016/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationMain.class})
@WebAppConfiguration
public class AuthenticationControllerTest {
//    @Resource
//     categoryService;
//
//    @Test
//    public void testQueryCategory(){
//        List<Category> categories = categoryService.queryCategory(0, 20);
//        System.out.println(categories);
//
//    }
}
