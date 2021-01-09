package com.td;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.td.mongoDao.HotDao;
import com.td.mongoDao.HotDaoImpl;
import com.td.mongoDao.NewsDao;
import com.td.mongoDao.NewsDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class YqjkApplicationTests {
    @Test
    void contextLoads()  {
        HotDao hotDao=new HotDaoImpl();
        System.out.println(hotDao.queryHotsByWeb("baidu"));
    }}
