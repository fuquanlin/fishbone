package cn.fql.head.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * Created by fuquanlin on 13/09/2017.
 */
@RestController
public class TestController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Value("${account-service.url}")
    String accountUrl;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test(HttpSession session) {
        String result = restTemplate.getForObject(accountUrl+"test",String.class);
        return result;
    }
}
