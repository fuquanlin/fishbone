package cn.fql.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2017/1/5.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(EurekaMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Eureka server started!");
        sw.stop();
        System.out.println("Eureka Launch Time: " + sw.toString());
    }
}
