package com.td.service;

import com.td.pojo.Hot;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface HotService {
    /**
     * 通过网站搜索热点
     * @param  web
     * @return List<Hot>
     */
    public List<Hot> queryHotsByWeb(String web);
}
