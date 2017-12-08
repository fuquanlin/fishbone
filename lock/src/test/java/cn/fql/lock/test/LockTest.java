package cn.fql.lock.test;

import cn.fql.lock.LockCallBack;
import cn.fql.lock.LockMain;
import cn.fql.lock.service.LockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fuquanlin on 06/12/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LockMain.class})
@WebAppConfiguration
public class LockTest {

    @Autowired
    LockService lockService;

    @Test
    public void testLock() throws Exception {
        lockService.lockProcess(() -> {
            System.out.println("Done");
            return "sdsd";
        }, "test", 5);
        System.out.println("test lock");
    }

    @Test
    public void testLockWithLocktime() {
        int threadNums = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadNums);
        LockCallBack<String> callback = () -> {
            System.out.println("test");
            return "test";
        };
        for (int i = 0; i < threadNums; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    // RedisAutoConfiguration
                    // distributedLockService.doProcess(callback, lockKey, 3);
                    System.out.println("before:" + j);
                    lockService.lockProcessWithResult(callback, "test", 2);
                    System.out.println("end:" + j);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("test end");
    }


    @Test
    public void testLockWithNoLocktime() {
        int threadNums = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadNums);
        LockCallBack<String> callback = () -> {
            System.out.println("test");
            return "test";
        };
        for (int i = 0; i < threadNums; i++) {
            new Thread(() -> {
                try {
                    // distributedLockService.doProcess(callback, lockKey, 3);
                    lockService.lockProcess(callback, "test", -1);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("test end");
    }
}
