package com.td.service;

import com.td.mongoDao.NewsDao;
import com.td.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsDao newsDao;

    @Autowired
    NewsService newsService;

    @Override
    public List<News> findAllNews(Integer page,String key) {
        return newsDao.findAllNews(page,key);
    }

    @Override
    public Integer findAllNewsLength(String key) {
        return newsDao.findAllNewsLength(key);
    }

    @Override
    public List<News> findAllXinlang(Integer page,String key) {
        return newsDao.findAllXinlang(page,key);
    }

    @Override
    public Integer findAllXinlangLength(String key) {
        return newsDao.findAllXinlangLength(key);
    }

    @Override
    public List<News> findAllToutiao(Integer page,String key) {
        return newsDao.findAllToutiao(page,key);
    }

    @Override
    public Integer findAllToutiaoLength(String key) {
        return  newsDao.findAllToutiaoLength(key);
    }

    @Override
    public List<News> findAllWeixin(Integer page, String key) {
        return newsDao.findAllWeixin(page,key);
    }

    @Override
    public Integer findAllWeixinLength(String key) {
        return newsDao.findAllWeixinLength(key);
    }

    @Override
    public void insertKeywords(String kw0, String kw1) {
        System.out.println("length"+newsService.queryNewBykw(kw0,kw1));
        if (newsService.queryNewBykw(kw0,kw1)<1)  //如果不重叠再添加
           {newsDao.insertKeywords(kw0,kw1);}
    }

    @Override
    public Integer queryNewBykw(String kw0, String kw1) {
        return newsDao.queryNewBykw(kw0,kw1);
    }

    @Override
    public News queryNewsDeatil(String id) {
        return newsDao.findNewsById(id);
    }
}
