package cn.fql.ribbon.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fuquanlin on 2017/1/16.
 */
@FeignClient(name = "fishbone")
public interface TestFeignClient {

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    String printConfig();
}

