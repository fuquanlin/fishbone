package cn.fql.head;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuquanlin on 06/09/2017.
 */

@SpringBootApplication
@EnableOAuth2Sso
@EnableEurekaClient
public class HeadMain {

    @Bean
    OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        SpringApplication.run(HeadMain.class, args);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + "Head service server started!");
        sw.stop();
        System.out.println("Head service Launch Time: " + sw.toString());
    }
}
