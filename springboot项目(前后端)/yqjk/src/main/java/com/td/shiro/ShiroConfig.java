package com.td.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 * @author  kari
 */
@Component
@Configuration
public class ShiroConfig {

    /**
     *创建ShiroFilterFactoryBean
     * @param securityManager
     * @return  shiroFilterFactoryBean;
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /**
         * Shiro内置过滤器:可以实现权限相关的拦截器
         *常用的过滤器:
         * anon:无需认证(登录) 可以访问
         * authc:必须认证才可以访问
         * user:如果使用remeber me的功能可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        //加入过滤器（拦截和过滤的都是请求）
        //数据拦截
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/data/allNews","perms[normal]");
        filterMap.put("/data/allXinlangNews","perms[normal]");
        filterMap.put("/data/allToutiaoNews","perms[normal]");
        filterMap.put("/data/updateKeywords","perms[normal]");
        //访问拦截
        filterMap.put("/view/toLogin","anon");
        filterMap.put("/view/toRegiste","anon");
        filterMap.put("/view/toRetrieve","anon");
        filterMap.put("/view/noAuth","anon");
        filterMap.put("/view/*","authc");
        filterMap.put("/*","authc");
        //设置跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/view/toLogin");
        //设置未授权的提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/view/noAuth");
        //配置入过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 创建  DefaultWebSecurityManager
     * @param userRealm
     * @return securityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联一个Realm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }


    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}