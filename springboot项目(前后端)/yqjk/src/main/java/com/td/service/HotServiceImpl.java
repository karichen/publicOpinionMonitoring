package com.td.service;

import com.td.mongoDao.HotDao;
import com.td.pojo.Hot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotServiceImpl implements HotService {

    @Autowired
    HotDao hotDao;

    @Override
    public List<Hot> queryHotsByWeb(String web) {
        return hotDao.queryHotsByWeb(web);
    }
}
