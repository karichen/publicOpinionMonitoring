package com.td.controller;


import com.alibaba.fastjson.JSONObject;
import com.td.pojo.User;
import com.td.service.NewsService;
import com.td.service.UserService;
import com.td.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author kari
 * 2020/4/3
 * 控制视图的controller
 */
@RequestMapping("/view")
@Controller
public class ViewController {
    @Autowired
    CookieUtil cookieUtil;

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    User user;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }


   /* @RequestMapping("/toHome")
    public String toHome(HttpServletRequest request, HttpServletResponse response){
        return "home";
    }*/


    @RequestMapping("/toKeyword")
    public String toHome(HttpServletRequest request, HttpServletResponse response) {
        String view="login";
        //获取cookie 判定是否有该用户的登陆凭证  如果有登陆凭证 立马跳转到登陆视图
        String useremail=cookieUtil.getCookieValue(request,"useremail");
        String password=cookieUtil.getCookieValue(request,"password");
            user.setUseremail(useremail);
            user.setPassword(password);
            if (userService.queryUser(user)!=null){
                view ="keyword";
            }
        return view;
    }

    @RequestMapping("/toRegiste")
    public String toRegiste(){
        return "registe";
    }

    @RequestMapping("/toRetrieve")
    public String toRetrieve(){
        return "retrieve";
    }

    @RequestMapping("/toNewsDetail")
    public String toNewsDetail(@RequestParam("id")String id, HttpServletRequest request, Model model){
        String view="home";
        //获取cookie 判定是否有该用户的登陆凭证  如果有登陆凭证 立马跳转到登陆视图
        String useremail=cookieUtil.getCookieValue(request,"useremail");
        String password=cookieUtil.getCookieValue(request,"password");
        user.setUseremail(useremail);
        user.setPassword(password);
        if (userService.queryUser(user)!=null){
            view ="newsDetail";
            model.addAttribute("news", newsService.queryNewsDeatil(id));
        }
        System.out.println(view);
        return view;
    }

    @RequestMapping("/public/toPublicHome")
    public String toPublicHome(HttpServletRequest request){
        String view="login";
        //获取cookie 判定是否有该用户的登陆凭证  如果有登陆凭证 立马跳转到登陆视图
        String useremail=cookieUtil.getCookieValue(request,"useremail");
        String password=cookieUtil.getCookieValue(request,"password");
        user.setUseremail(useremail);
        user.setPassword(password);
        if (userService.queryUser(user)!=null){
            view ="public/public-home";
        }

        return view;
    }

    @RequestMapping("/public/toPublicForecast")
    public String toPublicForecast(){
        return "public/public-forecast";
    }

    @RequestMapping("/public/toPublicHotNews")
    public String toPublicHotNews(){
        return "public/public-hot-news";
    }

    @RequestMapping("/public/toPublicRumor")
    public String toPublicRumor(){
        return "public/public-rumor";
    }

    @RequestMapping("/public/toPublicService")
    public String toPublicService(){
        return "public/public-service";
    }

    @RequestMapping("/realTime/toRealTime")
    public String toRealTime(){
        return "realTime/realTime";
    }

    @RequestMapping("/realTime/toRealTimeDetail")
    public String toRealTimeDetail(){
        return "realTime/realTimeDetail";
    }

}
