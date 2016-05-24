package cn.fql.fishbone.shiro;

import cn.fql.fishbone.dao.UserDAO;
import cn.fql.fishbone.model.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class DBShiroRealm extends AuthorizingRealm {

    @Autowired
    UserDAO userDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String) super.getAvailablePrincipal(principalCollection);

        User user = userDAO.getUserByName(loginName);
        if (user != null) {
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //:todo role and permission
//            info.setRoles(user.getRolesName());
//            info.addStringPermission();
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userDAO.getUserByName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }
}
