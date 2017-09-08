package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.util.ResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuquanlin on 08/09/2017.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<Void> test(){
        return ResultBuilder.success();
    }
}
