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
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Repository
public class NewsDaoImpl implements NewsDao {

    @Autowired
    MongoDBUtil mongoDBUtil;

    @Autowired
    News news;

    @Autowired
    TimeUtil timeUtil;

    @Override
    public List<News> findAllNews(Integer page,String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        FindIterable<Document> documents = collections.find(bson).limit(5).skip(5*(page-1)).sort(new BasicDBObject("date",-1));
        List<News> newsList=new CopyOnWriteArrayList<>();
        for (Document document : documents) {
            JSONObject jsonObj = new JSONObject(document);
            String id=jsonObj.getString("_id");
            String title=jsonObj.getString("title");
            String url=jsonObj.getString("url");
            String web=jsonObj.getString("web");
            String kw0=jsonObj.getJSONArray("kw").getString(0);
            String kw1=jsonObj.getJSONArray("kw").getString(1);
            Date date=jsonObj.getDate("date");
            String writer=jsonObj.getString("writer");
            String text=jsonObj.getString("text");
            news=new News(id,title,url,kw0,kw1,web,date,writer,text);
            newsList.add(news);
        }
        mongoDBUtil.close(client);
        return newsList;
    }

    @Override
    public Integer findAllNewsLength(String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        Integer length = Math.toIntExact(collections.countDocuments(bson));
        mongoDBUtil.close(client);
        return length;
    }

    @Override
    public List<News> findAllXinlang(Integer page,String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web","xinlang");
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        FindIterable<Document> documents = collections.find(bson).limit(5).skip(5*(page-1)).sort(new BasicDBObject("date",-1));
        List<News> newsList=new CopyOnWriteArrayList<>();
        for (Document document : documents) {
            JSONObject jsonObj = new JSONObject(document);
            String id=jsonObj.getString("_id");
            String title=jsonObj.getString("title");
            String url=jsonObj.getString("url");
            String web=jsonObj.getString("web");
            String kw0=jsonObj.getJSONArray("kw").getString(0);
            String kw1=jsonObj.getJSONArray("kw").getString(1);
            Date date=jsonObj.getDate("date");
            String writer=jsonObj.getString("writer");
            String text=jsonObj.getString("text");
            news=new News(id,title,url,kw0,kw1,web,date,writer,text);
            newsList.add(news);
        }
        mongoDBUtil.close(client);
        return newsList;
    }

    @Override
    public Integer findAllXinlangLength(String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web","xinlang");
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        Integer length = Math.toIntExact(collections.countDocuments(bson));
        mongoDBUtil.close(client);
        return length;
    }

    @Override
    public List<News> findAllToutiao(Integer page,String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web","toutiao");
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        FindIterable<Document> documents = collections.find(bson).limit(5).skip(5*(page-1)).sort(new BasicDBObject("date",-1));
        List<News> newsList=new CopyOnWriteArrayList<>();
        for (Document document : documents) {
            JSONObject jsonObj = new JSONObject(document);
            String id=jsonObj.getString("_id");
            String title=jsonObj.getString("title");
            String url=jsonObj.getString("url");
            String web=jsonObj.getString("web");
            String kw0=jsonObj.getJSONArray("kw").getString(0);
            String kw1=jsonObj.getJSONArray("kw").getString(1);
            Date date=jsonObj.getDate("date");
            String writer=jsonObj.getString("writer");
            String text=jsonObj.getString("text");
            news=new News(id,title,url,kw0,kw1,web,date,writer,text);
            newsList.add(news);
        }
        mongoDBUtil.close(client);
        return newsList;
    }

    @Override
    public Integer findAllToutiaoLength(String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web","toutiao");
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        Integer length = Math.toIntExact(collections.countDocuments(bson));
        mongoDBUtil.close(client);
        return length;
    }

    @Override
    public List<News> findAllWeixin(Integer page, String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web","weixin");
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        FindIterable<Document> documents = collections.find(bson).limit(5).skip(5*(page-1)).sort(new BasicDBObject("date",-1));
        List<News> newsList=new CopyOnWriteArrayList<>();
        for (Document document : documents) {
            JSONObject jsonObj = new JSONObject(document);
            String id=jsonObj.getString("_id");
            String title=jsonObj.getString("title");
            String url=jsonObj.getString("url");
            String web=jsonObj.getString("web");
            String kw0=jsonObj.getJSONArray("kw").getString(0);
            String kw1=jsonObj.getJSONArray("kw").getString(1);
            Date date=jsonObj.getDate("date");
            String writer=jsonObj.getString("writer");
            String text=jsonObj.getString("text");
            news=new News(id,title,url,kw0,kw1,web,date,writer,text);
            newsList.add(news);
        }
        mongoDBUtil.close(client);
        return newsList;
    }

    @Override
    public Integer findAllWeixinLength(String key) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("web","weixin");
        Pattern pattern = Pattern.compile("^.*"+key+".*$", Pattern.CASE_INSENSITIVE);//正则匹配
        bson.append("title", new BasicDBObject("$regex", pattern));
        Integer length = Math.toIntExact(collections.countDocuments(bson));
        mongoDBUtil.close(client);
        return length;
    }

    @Override
    public void insertKeywords(String kw0, String kw1) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "time");
        Map<String,Object> map=new HashMap<String, Object>();
        ArrayList kw=new ArrayList();
        kw.add(kw0);
        kw.add(kw1);
        map.put("kw",kw);
        map.put("hour",timeUtil.getHour(new Date()));
        map.put("min",timeUtil.getMinute(new Date())+1);
        Document document=new Document(map);
        collections.insertOne(document);
        mongoDBUtil.close(client);
    }

    @Override
    public Integer queryNewBykw(String kw0, String kw1) {
        ArrayList kw=new ArrayList();
        kw.add(kw0);
        kw.add(kw1);
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "time");
        BasicDBObject bson=new BasicDBObject();
        bson.append("kw",kw);
        System.out.println("kw"+kw);
        Integer length = Math.toIntExact(collections.countDocuments(bson));
        mongoDBUtil.close(client);
        return length;
    }

    @Override
    public News findNewsById(String id) {
        MongoDBUtil mongoDBUtil=new MongoDBUtil();
        MongoClient client = mongoDBUtil.getClient();
        MongoDatabase db = mongoDBUtil.getDatabase("test");
        MongoCollection<Document> collections = mongoDBUtil.getCollections(db, "news");
        BasicDBObject bson=new BasicDBObject();
        bson.append("_id",new ObjectId(id));
        FindIterable<Document> documents = collections.find(bson);
        for (Document document : documents) {
            JSONObject jsonObj = new JSONObject(document);
            String _id=jsonObj.getString("_id");
            String title=jsonObj.getString("title");
            String url=jsonObj.getString("url");
            String web=jsonObj.getString("web");
            String kw0=jsonObj.getJSONArray("kw").getString(0);
            String kw1=jsonObj.getJSONArray("kw").getString(1);
            Date date=jsonObj.getDate("date");
            String writer=jsonObj.getString("writer");
            String text=jsonObj.getString("text");
            news=new News(_id,title,url,kw0,kw1,web,date,writer,text);
        }
        mongoDBUtil.close(client);
        return news;
    }


}
