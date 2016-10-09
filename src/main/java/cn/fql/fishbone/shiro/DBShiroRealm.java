package cn.fql.fishbone.shiro;

import cn.fql.fishbone.dao.PermissionDAO;
import cn.fql.fishbone.dao.RoleDAO;
import cn.fql.fishbone.dao.UserDAO;
import cn.fql.fishbone.model.domain.Permission;
import cn.fql.fishbone.model.domain.Role;
import cn.fql.fishbone.model.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public class DBShiroRealm extends AuthorizingRealm {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    PermissionDAO permissionDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String) super.getAvailablePrincipal(principalCollection);

        User user = userDAO.getUserByName(loginName);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> userRolesByIds = roleDAO.getUserRolesById(user.getId());
            Set roleSet = new HashSet<>();
            for (Role userRolesById : userRolesByIds) {
                roleSet.add(userRolesById.getRolename());
                List<Permission> permissionsByRoleId = permissionDAO.getPermissionsByRoleId(userRolesById.getId());
                for (Permission permission : permissionsByRoleId) {
                    info.addStringPermission(permission.getPermissionname());
                }
            }
            info.setRoles(roleSet);
            return info;
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
