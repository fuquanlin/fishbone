package cn.fql.fishbone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2016/5/22.
 */
@SpringBootApplication
@ComponentScan("cn.fql.fishbone")
public class ApplicationMain {


    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(ApplicationMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Fishbone service server started!");
        sw.stop();
        System.out.println("Fishbone Launch Time: " + sw.toString());
    }

}