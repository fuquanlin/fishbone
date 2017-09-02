package cn.fql.fishbone.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuquanlin on 2016/8/17.
 */
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String welcomeMsg() {
        return "Welcome to Fiahbone UI";
    }
}
