package cn.fql.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2017/1/5.
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulMain {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(ZuulMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Zuul server started!");
        sw.stop();
        System.out.println("Zuul Launch Time: " + sw.toString());
    }
}
