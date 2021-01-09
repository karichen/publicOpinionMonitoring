package com.td.mongoDao;

import com.td.pojo.Hot;
import com.td.pojo.News;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface NewsDao {
    /**
     * 查询分页新闻
     * @return List<News>
     */
    public List<News> findAllNews(Integer page,String key);

    /**
     * 查询所有新闻的条数
     * @return  Integer
     */
    public Integer findAllNewsLength(String key);

    /**
     * 查询所有新浪新闻
     * @return List<News>
     */
    public List<News> findAllXinlang(Integer page,String key);

    /**
     * 查询所有新浪新闻的条数
     * @return Integer
     */
    public Integer findAllXinlangLength(String key);

    /**
     * 查询所有头条新闻
     * @return List<News>
     */
    public List<News> findAllToutiao(Integer page,String key);


    /**
     * 查询所有头条新闻的条数
     * @return Integer
     */
    public Integer findAllToutiaoLength(String key);

    /**
     * 查询所有微信新闻
     * @return List<News>
     */
    public List<News> findAllWeixin(Integer page,String key);


    /**
     * 查询所有微信新闻的条数
     * @return Integer
     */
    public Integer findAllWeixinLength(String key);

    /**
     *
     * @param kw0 一级关键词
     * @param kw1  二级关键词
     * @return 状态
     */
    public void insertKeywords(String kw0,String kw1);

    /**
     * 查询是否重叠
     * @param kw0 一级关键词语
     * @param kw1 二级关键词
     * @return
     */
    public Integer queryNewBykw(String kw0,String kw1);

    /**
     * 通过id查询新闻的详细信息
     * @param id
     * @return  News
     */
    public  News findNewsById(String id);

}
