package cn.fql.ribbon;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 2017/1/12.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonMain {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(RibbonMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "ribbon server started!");
        sw.stop();
        System.out.println("ribbon Launch Time: " + sw.toString());
    }
}
