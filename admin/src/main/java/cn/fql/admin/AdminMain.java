package cn.fql.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2017/1/11.
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class AdminMain {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(AdminMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Admin server started!");
        sw.stop();
        System.out.println("Admin Launch Time: " + sw.toString());
    }
}
