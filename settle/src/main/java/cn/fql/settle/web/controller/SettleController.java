package cn.fql.settle.web.controller;

import cn.fql.settle.service.SettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by fuquanlin on 04/12/2017.
 */
@RestController
public class SettleController {

    @Autowired
    private SettleService settleService;

    @RequestMapping(value = "/settle", method = {RequestMethod.GET})
    public String settle() {
        //:todo set date
        settleService.generateMonthReport(0,0);
        return "account success";
    }
}
