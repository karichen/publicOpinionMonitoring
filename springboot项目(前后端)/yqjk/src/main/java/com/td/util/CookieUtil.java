package com.td.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieUtil {

    /**
     *
     * @param request
     * @param key  Cookie的键
     * @return   返回Cookie的值
     */
     public String getCookieValue(HttpServletRequest request,String key){
        String value="";
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(key)){
                    value =cookie.getValue();
                }
            }
        }
        return value;
    }

}
