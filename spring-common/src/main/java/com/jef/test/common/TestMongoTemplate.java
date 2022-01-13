package com.jef.test.common;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import java.util.ArrayList;
import java.util.List;

public class TestMongoTemplate {
    static MongoTemplate template;

    public static MongoTemplate getMongoTemplate() {
        if (template == null) {
            //构建mongodClient配置
            MongoClientSettings.Builder builder = MongoClientSettings.builder();
            //构建集群配置
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address = new ServerAddress("127.0.0.1", 27017);
            addresses.add(address);
            builder.applyToClusterSettings(builder1 -> {
                builder1.hosts(addresses);
            });
            //加密配置
            builder.credential(MongoCredential.createCredential("root", "all_test", "123456".toCharArray()));
            //创建client
            MongoClient client = MongoClients.create(builder.build());
            template = new MongoTemplate(new SimpleMongoClientDbFactory(client, "ownercloud"));
        }
        return template;
    }
}