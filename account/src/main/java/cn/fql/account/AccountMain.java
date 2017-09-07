package cn.fql.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by fuquanlin on 06/09/2017.
 */
@SpringBootApplication
@EnableResourceServer
public class AccountMain extends ResourceServerConfigurerAdapter {

}
