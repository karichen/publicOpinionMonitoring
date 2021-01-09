package com.td.shiro;

import com.td.pojo.User;
import com.td.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm
 * @author  kari
 */

public class UserRealm extends AuthorizingRealm {
    @Autowired
    User user;
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //到数据库查询当前登录用户用的授权字符串
        //获取当前登录用户
        Subject subject= SecurityUtils.getSubject();
        System.out.println("啊啊啊啊啊"+subject);
        User user=(User)subject.getPrincipal();
        User dbUser=userService.findByName(user.getUseremail());
        //授权校验
        info.addStringPermission(dbUser.getRoot());
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro逻辑判断,判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        User user=userService.findByName(token.getUsername());
        if (user==null){
            //用户名不存在
            return null;//shiro底层会抛出一个UnknownAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

}