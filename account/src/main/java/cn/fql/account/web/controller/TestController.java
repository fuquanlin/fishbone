package cn.fql.account.web.controller;

import cn.fql.account.dao.mapper.AcctTypeMapper;
import cn.fql.account.model.domain.AcctType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by fuquanlin on 13/09/2017.
 */
@RestController
public class TestController {

    @Autowired
    AcctTypeMapper acctTypeMapper;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test(HttpSession session) {
        return "account success";
    }

    @RequestMapping(value = "/test2", method = {RequestMethod.GET})
    public List<AcctType> test2(HttpSession session) {
        List<AcctType> acctTypes = acctTypeMapper.selectAll();
        return acctTypes;
    }
}
