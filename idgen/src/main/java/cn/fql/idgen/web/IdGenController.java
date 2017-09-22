package cn.fql.idgen.web;

import cn.fql.idgen.util.SnowFlakeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by fuquanlin on 22/09/2017.
 */
@RestController
public class IdGenController {

    @Resource
    SnowFlakeUtil snowFlakeUtil;

    @RequestMapping(value = "/idgen", method = {RequestMethod.GET})
    public String generate() {
        return snowFlakeUtil.nextId()+"";
    }
}
