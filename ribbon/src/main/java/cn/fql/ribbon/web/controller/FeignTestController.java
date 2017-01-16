package cn.fql.ribbon.web.controller;

import cn.fql.ribbon.web.feign.TestFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuquanlin on 2017/1/16.
 */
@RestController
public class FeignTestController {
    @Autowired
    private TestFeignClient testFeignClient;

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String printConfig() {
        String printConfig = testFeignClient.printConfig();
        return printConfig;
    }
}
