package com.jef.mongodb;

import com.jef.constant.BasicConstant;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * MongodbJava类测试
 */
public class MongodbJavaTest {

    /**
     * 批量插入测试
     */
    @Test
    public void insertManyDocumentsTest() {
        Document document = new Document("name", BasicConstant.USER_NAME).append("phone", BasicConstant.USER_PHONE);
        Document document2 = new Document("allName", new Document("firstName", BasicConstant.USER_NAME).append("lastName", "tu"));
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        documents.add(document2);
        MongodbJava.insertManyDocuments(BasicConstant.TEST_ALL_TABLE, documents);
    }

    /**
     * 批量删除测试
     */
    @Test
    public void deleteDocumentsTest() {
        MongodbJava.deleteDocuments(BasicConstant.TEST_ALL_TABLE);
    }

    /**
     * 批量修改测试
     */
    @Test
    public void updateDocumentsTest() {
        MongodbJava.updateDocuments(BasicConstant.TEST_ALL_TABLE);
    }

    /**
     * 批量查询测试
     */
    @Test
    public void findDocumentsTest() {
        MongodbJava.findDocuments(BasicConstant.TEST_ALL_TABLE);
    }

    /**
     * 过滤查询测试
     */
    @Test
    public void findDocumentTest() {
        MongodbJava.findDocument(BasicConstant.TEST_ALL_TABLE);
    }
}
