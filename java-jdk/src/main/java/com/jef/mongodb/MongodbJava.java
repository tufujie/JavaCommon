package com.jef.mongodb;


import com.jef.constant.BasicConstant;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * mongodb使用
 * @author Jef
 * @date 20180811
 */
public class MongodbJava {
    private static MongoDatabase mongoDatabase = null;

    /**
     * 创建mongodb连接
     * @return
     */
    public static MongoDatabase getConnectionMongodb() {
        if (mongoDatabase != null) {
            return mongoDatabase;
        }
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            // 方式2
            /*MongoClient mongoClientTwo = new MongoClient(Arrays.asList(
                    new ServerAddress("localhost", 27017),
                    new ServerAddress("localhost", 27018)));*/
            // 方式3
            /*MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017,localhost:27018");
            MongoClient mongoClientThree = new MongoClient(mongoClientURI);*/
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(BasicConstant.ALL_TEST_DB);
            System.out.println("Connect to database successfully");
            return mongoDatabase;

        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    /**
     * 创建mongodb集合
     */
    public static void createCollection(String collection) {
        MongoDatabase mongoDatabase = getConnectionMongodb();
        MongoCollection<Document> mongoCollection = getCollection(collection);
        if (mongoCollection != null) {
            System.out.println("collection " + collection + " 已存在，不再创建");
        } else {
            mongoDatabase.createCollection(collection);
            System.out.println("创建collection " + collection + " 成功");
        }
    }

    /**
     * 获取mongodb集合
     */
    public static MongoCollection<Document> getCollection(String collection) {
        MongoDatabase mongoDatabase = getConnectionMongodb();
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        if (mongoCollection == null) {
            System.out.println("collection " + collection + " 不存在，创建");
            createCollection(collection);
            return getCollection(collection);
        }
        System.out.println("collection " + collection + " 获取成功");
        return mongoCollection;
    }

    /**
     * 插入文档，单个插入
     */
    public static void insertOneDucument(String collection, Document document) {
        MongoCollection<Document> mongoCollection = getCollection(collection);
        mongoCollection.insertOne(document);
        System.out.println("单个插入成功");
    }

    /**
     * 插入文档，多个插入
     */
    public static void insertManyDocuments(String collection, List<Document> documents) {
        MongoCollection<Document> mongoCollection = getCollection(collection);
        mongoCollection.insertMany(documents);
        System.out.println("多个插入成功");
    }

    /**
     * 批量删除测试
     */
    /**
     * 批量修改测试
     */
    public static void deleteDocuments(String collection) {
        MongoCollection<Document> mongoCollection = getCollection(collection);
        System.out.println("未删除前");
        findDocuments(collection);
        // 将文档中name等于Jef的删除一个或全部
        mongoCollection.deleteOne(eq("name", BasicConstant.USER_NAME));
        mongoCollection.deleteMany(eq("name", BasicConstant.USER_NAME));
        System.out.println("删除之后");
        findDocuments(collection);
    }

    /**
     * 批量修改测试
     */
    public static void updateDocuments(String collection) {
        MongoCollection<Document> mongoCollection = getCollection(collection);
        System.out.println("未修改前");
        findDocuments(collection);
        // 将文档中name等于Jef的修改为name等于另一个字符串
        mongoCollection.updateMany(and(eq("name", BasicConstant.USER_NAME), eq("phone", BasicConstant.USER_PHONE)), new Document("$set", new Document("name", BasicConstant.STR_ONE)));
        System.out.println("修改之后");
        findDocuments(collection);
    }

    /**
     * 检索所有文档
     */
    public static void findDocuments(String collection) {
        MongoCollection<Document> mongoCollection = getCollection(collection);
        //检索所有文档
        /**
         * 1. 获取迭代器FindIterable<Document>
         * 2. 获取游标MongoCursor<Document>
         * 3. 通过游标遍历检索出的文档集合
         * */
        FindIterable<Document> findIterable = mongoCollection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            // 获取之后解析
            for (Map.Entry<String, Object> entry : document.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }
    }
    /**
     * 检索一个文档
     */
    public static void findDocument(String collection) {
        MongoCollection<Document> mongoCollection = getCollection(collection);
        Document document = mongoCollection.find(eq("name", BasicConstant.USER_NAME)).first();
        System.out.println(document.toJson());
    }

    /**
     *
     */
    public static void findSetDocument(String collection) {
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        MongoCollection<Document> mongoCollection = getCollection(collection);
        mongoCollection.find(and(eq("name", BasicConstant.USER_NAME), eq("phone", BasicConstant.USER_PHONE))).forEach(printBlock);
    }


}
