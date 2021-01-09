package com.td.mongoDao;


import com.td.pojo.Hot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotDao  {
    /**
     * 通过网站搜索热点
     * @param  web
     * @return List<Hot>
     */
    public  List<Hot> queryHotsByWeb(String web);
}
