package cn.fql.fishbone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2016/5/22.
 */
@SpringBootApplication
@ComponentScan("cn.fql.fishbone")
public class ApplicationMain {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "cobra service server started!");
    }

}