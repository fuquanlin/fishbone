package cn.fql.idgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 21/09/2017.
 */
@SpringBootApplication
@EnableEurekaClient
public class IdGenMain {

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(IdGenMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "IdGen service server started!");
        sw.stop();
        System.out.println("IdGen service Launch Time: " + sw.toString());
    }

}
