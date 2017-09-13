package cn.fql.account.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by fuquanlin on 13/09/2017.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test(HttpSession session) {
        return "account success";
    }
}
