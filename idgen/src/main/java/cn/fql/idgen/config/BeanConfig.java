package cn.fql.idgen.config;

import cn.fql.idgen.util.SnowFlakeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fuquanlin on 22/09/2017.
 */
@Configuration
public class BeanConfig {

    @Bean
    public SnowFlakeUtil snowFlakeUtil(){
        //:todo eureka dispatch id
        return new SnowFlakeUtil(1,1);
    }
}
