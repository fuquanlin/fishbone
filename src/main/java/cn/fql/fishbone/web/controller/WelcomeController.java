package cn.fql.fishbone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fuquanlin on 2016/8/17.
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        return "welcome";
    }
}
