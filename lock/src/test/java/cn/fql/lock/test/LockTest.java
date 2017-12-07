package cn.fql.lock.test;

import cn.fql.lock.service.LockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fuquanlin on 06/12/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LockMain.class)
public class LockTest {

    @Autowired
    LockService lockService;

    @Test
    public void testLock() throws Exception {
        lockService.lockProcess(()-> {System.out.println("Done");return "sdsd";},"test",5);
    }
}
