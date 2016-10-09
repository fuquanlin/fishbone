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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fuquanlin on 2016/9/29.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<PageResult<User>> queryUser(UserParam userParam) {
        List<User> users = userService.queryUser(userParam);
        return ResultBuilder.build(userParam,users);
    }

    @RequestMapping(value = "/{userId}", method = {RequestMethod.GET})
    public Result<User> getUserById(@PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        return ResultBuilder.build(user);
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
