package com.td.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author
 * 2020/4/3
 * @param
 * ：id Object_id
 *   title  新闻标题
 *   url  原文链接
 *   kw0  关键字
 *   kw1  关键字
 *   web  资源来源
 *   date  时间
 *   writer 作者
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    public String id;
    public String title;
    public  String url;
    public  String kw0;
    public String  kw1;
    public  String web;
    public  Date date;
    public  String writer;
    public  String text;
}
