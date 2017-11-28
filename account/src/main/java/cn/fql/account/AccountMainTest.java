package cn.fql.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 28/11/2017.
 */
@SpringBootApplication
public class AccountMainTest {

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(AccountMainTest.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Account service server started!");
        sw.stop();
        System.out.println("Account service Launch Time: " + sw.toString());
    }
}
