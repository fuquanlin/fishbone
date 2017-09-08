package cn.fql.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2017/1/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinStreamServer
public class SleuthMain {

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(SleuthMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Sleuth server started!");
        sw.stop();
        System.out.println("Sleuth Launch Time: " + sw.toString());
    }
}
