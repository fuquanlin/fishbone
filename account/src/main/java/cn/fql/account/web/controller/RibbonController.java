package cn.fql.account.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fuquanlin on 01/12/2017.
 */
@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/ribbontest", method = RequestMethod.GET)
    public String test() {
        return restTemplate.getForEntity("http://idgen-service/idgen", String.class).getBody();
    }
}
