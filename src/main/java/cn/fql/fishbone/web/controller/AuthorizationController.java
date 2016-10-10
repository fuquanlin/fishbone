package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.FishBoneConstants;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.util.ResultBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuquanlin on 2016/8/23.
 */
@RestController
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public Result getCurrentUserData(){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return ResultBuilder.build(SecurityUtils.getSubject().getSession().getAttribute(FishBoneConstants.AUTHORIZATION_SESSION));
        }else{
            return ResultBuilder.paramError("Authentication failed");
        }
    }
}
