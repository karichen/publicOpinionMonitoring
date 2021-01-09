package com.td.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MongoDBUtil {
    private static  MongoClient client =null;
    private static   MongoDatabase db=null;
    private static MongoCollection<Document> collections=null;
    //获取Mongodb连接

    public MongoClient getClient(){
        String user = "server";                   //用户名
        String database = "test";              //数据库
        char[] password = "L0veNjupt".toCharArray();   //密码
        MongoCredential credential = MongoCredential.createCredential(user,database,password);   //验证对象
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();     //连接操作对象
         client = new MongoClient(new ServerAddress("127.0.0.1",27017),credential,options);   //连接对象
        return client;
    }

    //获取操作数据库
    public MongoDatabase getDatabase(String dbName){
        db=client.getDatabase(dbName);
        return db;
    }

    //获取操作集合
    public MongoCollection<Document> getCollections(MongoDatabase db,String collectionName){
        collections=db.getCollection(collectionName);
        return collections;
    }

    //关闭连接
    public void close(MongoClient client){
        client.close();
    }

}
