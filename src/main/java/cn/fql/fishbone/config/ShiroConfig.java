package cn.fql.fishbone.config;

import cn.fql.fishbone.shiro.DBShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuquanlin on 2016/5/23.
 */
@Configuration
public class ShiroConfig {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl("/login");
//        shiroFilter.setSuccessUrl("/index.html");
        shiroFilter.setUnauthorizedUrl("/403");

        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("authc", new FormAuthenticationFilter());
        filters.put("user", new UserFilter());
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("perms", new PermissionsAuthorizationFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
        filterChainDefinitionMapping.put("/welcome/**", "authc,perms[welcome]");
        filterChainDefinitionMapping.put("/user/**", "authc,perms[user]");
        filterChainDefinitionMapping.put("/role/**", "authc,perms[role]");
        filterChainDefinitionMapping.put("/log/**", "authc,perms[log}");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        //Security Manager
        shiroFilter.setSecurityManager(securityManager());
        return shiroFilter;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public DBShiroRealm realm() {
        DBShiroRealm dbShiroRealm = new DBShiroRealm();
        dbShiroRealm.init();
        return dbShiroRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
