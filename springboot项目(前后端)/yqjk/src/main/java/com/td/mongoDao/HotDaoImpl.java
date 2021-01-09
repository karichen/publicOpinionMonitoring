package com.td.mongoDao;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.td.pojo.Hot;
import com.td.pojo.News;
import com.td.util.MongoDBUtil;
import com.td.util.TimeUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class HotDaoImpl implements HotDao {

    @Autowired
    MongoDBUtil mongoDBUtil;

    @Autowired
    News news;

    @Autowired
    TimeUtil timeUtil;


    @Override
    public List<Hot> queryHotsByWeb(String web) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "hotspot");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web",web);
        FindIterable<Document> documents = collections.find(bson);
        System.out.println(documents);
        List<Hot> hotList=new CopyOnWriteArrayList<>();
        for (Document document : documents) {
            JSONObject jsonObj = new JSONObject(document);
            for (int i = 0; i < jsonObj.getJSONArray("info").size(); i++) {
                String info=jsonObj.getJSONArray("info").get(i).toString();
                //String转Map
                Gson gson = new Gson();
                Map<String, Object> infoMap = new HashMap<String, Object>();
                infoMap = gson.fromJson(info, infoMap.getClass());
                String title = (String) infoMap.get("title");
                String url = (String) infoMap.get("url");
                Double rank=(Double) infoMap.get("rank");
                //组装成Hot
                Hot hot=new Hot(title,url,rank);
                //放入List
                hotList.add(hot);
            }
        }
        System.out.println(hotList);
        mongoDBUtil.close(client);
        return hotList;
    }
}
