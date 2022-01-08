package com.jef.util;

import com.google.common.collect.Maps;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 测试Elasticsearch工具类
 *
 * @author Jef
 * @date 2022/1/8
 */
public class EsUtilTest {

    @Test
    public void testGetDataByID() {
        TransportClient client = EsUtil.getClient();
        Map<String, Object> resultMap = EsUtil.getDataById(client, "lib", "user", "1");
        System.out.println(resultMap);
    }

    @Test
    public void testInsertData() {
        TransportClient client = EsUtil.getClient();
        Map<String, Object> userMap = Maps.newHashMap();
        userMap.put("name", "Xu");
        userMap.put("phone", "18390220003");
        IndexResponse indexResponse = EsUtil.insertData(client, "lib", "user", "3", userMap);
        System.out.println(indexResponse);
    }

    @Test
    public void testDelete() {
        TransportClient client = EsUtil.getClient();
        DeleteResponse deleteResponse = EsUtil.deleteData(client, "lib", "user", "3");
        System.out.println(deleteResponse);
    }

    @Test
    public void testUpdate() {
        TransportClient client = EsUtil.getClient();
        Map<String, Object> userMap = Maps.newHashMap();
        userMap.put("name", "Xu");
        userMap.put("phone", "18390220004");
        IndexResponse indexResponse = EsUtil.updateData(client, "lib", "user", "3", userMap);
        System.out.println(indexResponse);
    }

    @Test
    public void testUpdateV2() throws IOException, ExecutionException, InterruptedException {
        TransportClient client = EsUtil.getClient();
        UpdateResponse updateResponse = EsUtil.updateDataV2(client, "lib", "user", "3");
        System.out.println(updateResponse);
    }

}