package com.td.controller;
import com.td.pojo.Hot;
import com.td.pojo.News;
import com.td.pojo.PerPage;
import com.td.pojo.User;
import com.td.service.HotService;
import com.td.service.NewsService;
import com.td.service.UserService;
import com.td.util.CookieUtil;
import com.td.util.EmailCodeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/data")
@RestController
public class WebController {
@Autowired
CookieUtil cookieUtil;

@Autowired
UserService userService;

@Autowired
NewsService newsService;

@Autowired
PerPage perPage;

@Autowired
User user;

@Autowired
EmailCodeUtil emailCodeUtil;


@Autowired
HotService  hotService;

@RequestMapping("/allNews")
public PerPage allNews(@RequestParam(value="page",required = false, defaultValue ="1")Integer page,
                       @RequestParam(value="key",required = false, defaultValue ="")String key,
                       HttpServletRequest request){
    String useremail=cookieUtil.getCookieValue(request,"useremail");
    String password=cookieUtil.getCookieValue(request,"password");
    user.setUseremail(useremail);
    user.setPassword(password);
    if (userService.queryUser(user)==null){
        return  perPage;
    }
    String keywords=userService.queryUser(user).getKeywords();
    perPage.setNewsList(newsService.findAllNews(page,key));
    perPage.setPageNum(newsService.findAllNewsLength(key));
    perPage.setKeywords(keywords);
    return perPage;
}

@RequestMapping("/allXinlangNews")
public PerPage allXinlangNews(@RequestParam(value="page",required = false, defaultValue ="1") Integer page,
                              @RequestParam(value="key",required = false, defaultValue ="")String key,
                              HttpServletRequest request){
    String useremail=cookieUtil.getCookieValue(request,"useremail");
    String password=cookieUtil.getCookieValue(request,"password");
    user.setUseremail(useremail);
    user.setPassword(password);
    if (userService.queryUser(user)==null){
        return  perPage;
    }
    perPage.setNewsList(newsService.findAllXinlang(page,key));
    perPage.setPageNum(newsService.findAllXinlangLength(key));
    return perPage;
}

@RequestMapping("/allToutiaoNews")
public PerPage allToutiaoNews(@RequestParam(value="page",required = false, defaultValue ="1") Integer page,
                              @RequestParam(value="key",required = false, defaultValue ="")String key,
                              HttpServletRequest request){
    String useremail=cookieUtil.getCookieValue(request,"useremail");
    String password=cookieUtil.getCookieValue(request,"password");
    user.setUseremail(useremail);
    user.setPassword(password);
    if (userService.queryUser(user)==null){
        return  perPage;
    }
    perPage.setNewsList(newsService.findAllToutiao(page,key));
    perPage.setPageNum(newsService.findAllToutiaoLength(key));
    return perPage;
}


    @RequestMapping("/allWeixinNews")
    public PerPage allWeixinNews(@RequestParam(value="page",required = false, defaultValue ="1") Integer page,
                                  @RequestParam(value="key",required = false, defaultValue ="")String key,
                                  HttpServletRequest request){
        String useremail=cookieUtil.getCookieValue(request,"useremail");
        String password=cookieUtil.getCookieValue(request,"password");
        user.setUseremail(useremail);
        user.setPassword(password);
        if (userService.queryUser(user)==null){
            return  perPage;
        }
        perPage.setNewsList(newsService.findAllWeixin(page,key));
        perPage.setPageNum(newsService.findAllWeixinLength(key));
        return perPage;
    }

    /**
     *
     * @param useremail 用户邮箱
     * @param request
     * @return flag:
     *              send ok:发送成功
     *              same mail: 邮箱已经存在
     *
     */
    @RequestMapping("/requestCode")
public String requestCode(@RequestParam(name = "useremail")String useremail, HttpServletRequest request){
    Integer count=userService.countUserByUseremail(useremail);
    if (count>0){//邮箱已经存在
        return "same mail";
    }else {
        String code = emailCodeUtil.createCode();//生成随机码

        //将 本次验证码请求的useremail和真实产生的验证码作为kv对 存入map=>session
        HashMap map=new HashMap();
        map.put(useremail,code);
        HttpSession session=request.getSession();
        session.setAttribute(useremail,map);
        emailCodeUtil.sendCode(code, useremail);
        return "send ok";
    }
}

    /**
     *
     * @param useremail 用户邮箱
     * @param usercode  用户填写的验证码
     * @param password  密码
     * @param request
     * @return  flag:
     *         error code :验证码不匹配
     *         fail:  未知错误
     *         same mail: 邮箱已经存在
     *         success:操作成功
     *
     */
    @RequestMapping("/registe")
public String registe(@RequestParam(name = "useremail")String useremail,
                      @RequestParam(name = "usercode")String usercode,
                      @RequestParam(name = "password")String password,
                      HttpServletRequest request){
        String flag="fail";
        try{
            HashMap <String,Object> map= (HashMap) request.getSession().getAttribute(useremail);
            String realcode= (String) map.get(useremail);
            System.out.println(realcode);
            if (!usercode.toLowerCase().equals(realcode.toLowerCase())){//验证码不正确
                flag= "error code";
            }else {
                user.setUseremail(useremail);
                user.setPassword(password);
                user.setRoot("normal");
                flag =userService.addUser(user);
            }
        }catch (Exception e){
            flag="fail";
            e.printStackTrace();
        }

    return flag;
}

    /**
     *
     * @param request
     * @return code 随机前端校验验证码
     */
    @RequestMapping("/loginCode")
    public String loginCode(HttpServletRequest request) {
        return emailCodeUtil.createCode();//节省代码 使用邮箱工具类的随机code方法

    }

    /**
     *
     * @param useremail
     * @param password
     * @return Map  :
     *           Map中 flag :  no mail 不存在此邮箱
     *                         error pwd 密码错误
     *                         success 密码正确 登陆判定成功
     *           Map中user: 用户
     */
    @RequestMapping("/checkLogin")
    public HashMap checkLogin(@RequestParam(name = "useremail")String useremail,
                              @RequestParam(name = "password")String password,
                               HttpServletRequest request, HttpServletResponse response  )  {
        String flag="fail";
        System.out.println("用户名"+useremail);
        /**
         * 使用shiro编写认证操作
         */
        //1获取Subject
        Subject subject= SecurityUtils.getSubject();

        //2封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(useremail,password);

        //3.shiro执行登录操作
        try {
            subject.login(token);
            //登录成功
            flag="success";//密码正确 登陆判定成功
            //封装客户端所用的cookie通信
            Cookie cookie1=new Cookie("useremail",useremail);
            cookie1.setPath("/");
            Cookie cookie2=new Cookie("password",password);
            cookie2.setPath("/");
            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }catch (UnknownAccountException e){
            //登录失败:用户名不存在
            flag="no mail";//不存在此邮箱
        }catch (IncorrectCredentialsException e){
            //登录失败:密码错误
            flag="error pwd";//密码错误
        }
        HashMap map=new HashMap();
        map.put("flag",flag);
        map.put("user",user);
        return map;
    }

    /**
     *
     * @param useremail 用户邮箱
     * @param request
     * @return flag:
     *              send ok:发送成功
     *              no mail: 账号不存在
     *
     */
    @RequestMapping("/requestCodeAgain")
    public String requestCodeAgain(@RequestParam(name = "useremail")String useremail, HttpServletRequest request){
        Integer count=userService.countUserByUseremail(useremail);
        System.out.println(count);
        if (count<1){//邮箱不存在
            return "no mail";
        }else {
            String code = emailCodeUtil.createCode();//生成随机码

            //将 本次验证码请求的useremail和真实产生的验证码作为kv对 存入map=>session
            HashMap map=new HashMap();
            map.put(useremail,code);
            HttpSession session=request.getSession();
            session.setAttribute(useremail,map);
            emailCodeUtil.sendCode(code, useremail);
            return "send ok";
        }
    }

    /**
     *
     * @param useremail 用户邮箱
     * @param usercode  用户填写的验证码
     * @param password  密码
     * @param request
     * @return  flag:
     *         error code :验证码不匹配
     *         fail:  未知错误
     *         no mail: 邮箱不存在
     *         success:操作成功
     *
     */
    @RequestMapping("/retrieve")
    public String retrieve(@RequestParam(name = "useremail")String useremail,
                          @RequestParam(name = "usercode")String usercode,
                          @RequestParam(name = "password")String password,
                          HttpServletRequest request){
        String flag="fail";
        try {
            HashMap <String,Object> map= (HashMap) request.getSession().getAttribute(useremail);
            String realcode= (String) map.get(useremail);
            System.out.println(realcode);
            if (!usercode.toLowerCase().equals(realcode.toLowerCase())){//验证码不正确
                flag= "error code";
            }else {
                user.setUseremail(useremail);
                user.setPassword(password);
                user.setRoot("normal");
                flag =userService.updatePassword(user);
            }
        }catch (Exception e){
            flag="fail";
            e.printStackTrace();
        }

        return flag;
    }

    /**
     *更新数据库keywords信息
     * @param keywords 关键词
     * @param request
     * @return flag：fail:操作失败
     *               success:操作成功
     */
    @RequestMapping("/updateKeywords")
    public String  updateKeywords(@RequestParam(name = "keywords")String keywords,HttpServletRequest request){
        String flag="fail";
        String useremail=cookieUtil.getCookieValue(request,"useremail");
        String password=cookieUtil.getCookieValue(request,"password");
        user.setUseremail(useremail);
        user.setPassword(password);
        if (userService.queryUser(user)!=null){
            user.setUseremail(useremail);
            user.setPassword(password);
            user.setKeywords(keywords);
            flag=userService.updateKeywords(user);
        }
        JSONArray json = JSONArray.fromObject(keywords);
        if (json.size()>0) {
            System.out.println("开始分拣");
            for (int i= 0; i< json.size(); i++) {
            JSONObject job = json.getJSONObject(i);
            String keywordFile = (String) job.get("keywordFile");
            System.out.println(keywordFile + "============================");
            JSONArray jsonArray = (JSONArray) job.get("keyword");
            if (jsonArray.size() > 0) {
                System.out.println("开始插入检索词");
                for (int j= 0; j < jsonArray.size(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    System.out.println(jsonObject.get("content"));
                    newsService.insertKeywords(keywordFile, (String) jsonObject.get("content"));
                }
            }
        }}
       return flag;
    }

    /**
     *  根据新闻id详细信息
     * @param id
     * @return News
     */
    @PostMapping("/newsDetail")
    public News newsDetail(@RequestParam("id") String id) {
        return newsService.queryNewsDeatil(id);
    }


    /**
     * 根据网站名称获取爬虫爬取的热点
     * @param web
     * @return List<Hot>
     */
    @PostMapping("/queryHot")
    public List<Hot> queryHot(@RequestParam("web") String web){
        return hotService.queryHotsByWeb(web);
    }
}
