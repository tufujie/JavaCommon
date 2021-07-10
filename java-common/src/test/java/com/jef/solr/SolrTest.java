package com.jef.solr;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jef.entity.TestAll;
import com.jef.entity.User;
import com.jef.util.SolrUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * solr测试
 * @author Jef
 * @date 2019/11/5
 */
public class SolrTest {

    /**
     * 获取solr数据
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testGetData() {
        Map<String, Object> queryParams = Maps.newHashMap();
        queryParams.put("name", "Jef");
        // 执行查询并返回response对象
        QueryResponse response = SolrUtil.search(queryParams, 0, 100, Lists.newArrayList(), Lists.newArrayList(), null, SolrUtil.USER_CORE_NAME, "id", "name", "phone", "age");
        SolrDocumentList list = response.getResults();
        // 遍历查询结果并输出
        for (SolrDocument solrDocument : list) {
            Long id = Long.valueOf((String) solrDocument.getFieldValue("id"));
            String name = (String) solrDocument.getFieldValue("name");
            String phone = (String) solrDocument.getFieldValue("phone");
            Integer age = Integer.valueOf((String) solrDocument.getFieldValue("age"));
            System.out.println("id=" + id + ",name=" + name + ",phone" + phone + ",age" + age);
        }
    }

    /**
     * 新增solr数据
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testAddUserData() throws IOException, SolrServerException {
        SolrClient client = SolrUtil.getSolrClient(SolrUtil.USER_CORE_NAME);
        // 封装实体对象
        User user = new User();
        user.setId(10000L);
        user.setName("solrInsert");
        user.setPhone("solrPhone");
        user.setAge(123);
        // 添加到solr
        client.addBean(user);
        // 提交后才生效
        client.commit();
    }

    /**
     * 新增solr数据
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testAddTestAllData() throws IOException, SolrServerException {
        SolrClient client = SolrUtil.getSolrClient(SolrUtil.TESTALL_CORE_NAME);
        // 封装实体对象
        TestAll testAll = new TestAll();
        testAll.setId(10000L);
        testAll.setTestName("solrInsert");
        testAll.setTestPhone("solrPhone");
        testAll.setData(123);
        // 添加到solr
        client.addBean(testAll);
        // 提交后才生效
        client.commit();
    }

    /**
     * 删除solr数据
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testDeleteData() throws IOException, SolrServerException {
        SolrClient client = SolrUtil.getSolrClient(SolrUtil.USER_CORE_NAME);
        client.deleteById("2");
        // 提交后才生效
        client.commit();
    }

    /**
     * 删除solr数据
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testDeleteDataV2() throws IOException, SolrServerException {
        String query = "name:Jef";
        SolrClient client = SolrUtil.getSolrClient(SolrUtil.USER_CORE_NAME);
        client.deleteByQuery(query);
        // 提交后才生效
        client.commit();
    }

    /**
     * 更新solr数据，先删后增
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testUpdateDataV2() throws IOException, SolrServerException {
        String query = "name:Jef";
        SolrClient client = SolrUtil.getSolrClient(SolrUtil.USER_CORE_NAME);
        client.deleteByQuery(query);
        // 提交后才生效
        client.commit();
        // 封装实体对象
        User user = new User();
        user.setId(10000L);
        user.setName("solrInsertV2");
        user.setPhone("solrPhoneV2");
        user.setAge(123);
        // 添加到solr
        client.addBean(user);
        // 提交后才生效
        client.commit();
    }
}