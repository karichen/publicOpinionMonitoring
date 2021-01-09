package com.td.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailCodeUtil {

@Autowired
JavaMailSenderImpl mailSender;

 public void sendCode(String code,String useremail){

     SimpleMailMessage message = new SimpleMailMessage();
     message.setSubject("注册验证码");
     message.setText("舆情监控系统 注册验证码是：" + code);
     message.setFrom("1580776594@qq.com");
     message.setTo(useremail);
     mailSender.send(message);
 }

 public String createCode(){
     String str="abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
     Random r=new Random();
     String arr[]=new String [4];
     String b="";
     for(int i=0;i<4;i++)
     {
         int n=r.nextInt(62);

         arr[i]=str.substring(n,n+1);
         b+=arr[i];

     }
     return b;
 }
}
