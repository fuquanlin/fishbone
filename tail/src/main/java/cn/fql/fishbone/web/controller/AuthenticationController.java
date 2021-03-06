package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.FishBoneConstants;
import cn.fql.fishbone.model.domain.User;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.util.ResultBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static cn.fql.fishbone.util.FishBoneSecurityUtil.getPermisionsFromSubject;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@RestController
@RefreshScope
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(User user) {
        String username = user.getUsername();
        String sha1 = new Sha256Hash(user.getPassword(), FishBoneConstants.PASSWORD_SALT).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), sha1);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            currentUser.getSession().setAttribute(FishBoneConstants.AUTHORIZATION_SESSION, getPermisionsFromSubject(currentUser));
            return ResultBuilder.success();
        } else {
            token.clear();
            return ResultBuilder.paramError("Username or password is wrong!");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result loginUrl() {
        return ResultBuilder.authenticationError("Authentication failed!");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return ResultBuilder.success();
    }

    @Value("${test2}")
    private String config;

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String printConfig() {
        return "testsdfdsfsd";
        //return config;
    }
}
