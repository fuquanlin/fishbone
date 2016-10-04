package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.service.base.UserService;
import cn.fql.fishbone.util.ResultBuilder;
import cn.fql.fishbone.web.dto.UserParam;
import cn.fql.fishbone.web.dto.pagination.PageInfo;
import cn.fql.fishbone.web.dto.pagination.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by fuquanlin on 2016/9/29.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public Result<PageResult<User>> queryUser(UserParam userParam) {
        PageResult pagedResult = new PageResult();
        List<User> users = userService.queryUser(userParam);
        pagedResult.setRows(users);

        PageInfo pageInfo = new PageInfo(userParam.getPageIndex(),userParam.getPageCount());
        pagedResult.setPageInfo(pageInfo);
        return ResultBuilder.build(pagedResult);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<Void> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResultBuilder.success();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<Void> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResultBuilder.success();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public Result<Void> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return ResultBuilder.success();
    }
}
