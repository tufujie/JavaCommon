package com.jef.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jef.constant.BasicConstant;
import com.jef.dao.TestAllDao;
import com.jef.entity.TestAll;
import com.jef.util.ConnectSessionUtil;
import com.jef.util.DateTimeUtil;
import com.jef.util.StringUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 测试实体
 *
 * @author Jef
 * @create 2018/6/1 15:45
 */
public class TestAllTest {
    private static final Logger logger = LoggerFactory.getLogger(TestAllTest.class);

    /**
     * 测试单个插入实体类
     */
    @Test
    public void testInsertTest() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAllOne = new TestAll();
        testAllOne.setTestName(BasicConstant.USER_NAME);
        testAllOne.setTestPhone(BasicConstant.USER_PHONE);
        testAllDao.insertSelect(testAllOne);
        System.out.println("测试ID" + testAllOne.getId());
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 测试单个插入实体类
     */
    @Test
    public void testInsertTestTwo() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAllOne = new TestAll();
        testAllOne.setTestName(BasicConstant.USER_NAME);
        testAllOne.setTestPhone(BasicConstant.USER_PHONE);
        TestAll testAllDb = testAllDao.getByNameAndPhone(testAllOne.getTestName(), testAllOne.getTestPhone());
        if (testAllDb == null) {
            int rows = testAllDao.insert(testAllOne);
            System.out.println("新增成功，rows=" + rows);
            System.out.println("新增成功，生成id=" + testAllOne.getId());
        } else {
            System.out.println("已存在");
        }
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 批量插入
     */
    @Test
    public void testBatchInsertTest() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<TestAll> list = Lists.newArrayList();
        TestAll testAllOne = new TestAll();
        testAllOne.setTestName("002");
        testAllOne.setTestPhone("002");
        list.add(testAllOne);
        TestAll testAllTwo = new TestAll();
        testAllTwo.setTestName("003");
        testAllTwo.setTestPhone("003");
        list.add(testAllTwo);
        boolean flag = testAllDao.batchInsert(list);
        System.out.println("保存成功？" + flag);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 测试多次提交
     * 多次提交后会产生多条数据
     */
    @Test
    public void testBatchCommitTest() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAllOne = new TestAll();
        testAllOne.setTestName(BasicConstant.USER_NAME);
        testAllOne.setTestPhone(BasicConstant.USER_PHONE);
        testAllDao.insertSelect(testAllOne);
        System.out.println("测试ID" + testAllOne.getId());
        session.commit();
        testAllDao.insertSelect(testAllOne);
        System.out.println("测试ID" + testAllOne.getId());
        session.commit();
    }

    /**
     * 测试多次提交，存在异常回滚情况
     * 未commit之前数据不产生，commit后未产生异常，产生数据
     */
    @Test
    public void testBatchCommitWithRollBackTest() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAllOne = new TestAll();
        testAllOne.setTestName(BasicConstant.USER_NAME);
        testAllOne.setTestPhone(BasicConstant.USER_PHONE);
        testAllDao.insertSelect(testAllOne);
        session.commit();
        testAllDao.insertSelect(testAllOne);
        try {
            System.out.println(1 / 0);
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void testDeleteByIds() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<Long> ids = Lists.newArrayList();
        ids.add(1L);
        Boolean flag = testAllDao.deleteByIds(ids);
        System.out.println(flag);
    }

    /**
     * 查询
     */
    @Test
    public void testSelecctByPrimaryKey() {
        logger.info("testLog");
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAll = testAllDao.selectByPrimaryKeyThird(1L);
        System.out.println("测试用户名=");
        System.out.println(testAll.getTestName());
        Map<String, Object> objectMap = Maps.newHashMap();
        objectMap.put("description", StringUtils.replaceAll(testAll.getTestName(), "\n", "<br/>", true));
        System.out.println(objectMap);
        // 同样的查询，使用了一级缓存
        TestAll testAllTwo = testAllDao.selectByPrimaryKeyThird(1L);
        System.out.println("测试用户名2");
        System.out.println(testAllTwo.getTestName());

    }

    /**
     * 查询，测试二级缓存
     */
    @Test
    public void testCache2() {
        SqlSession session1 = ConnectSessionUtil.getSqlSession();
        session1.getMapper(TestAllDao.class).selectByPrimaryKeyThird(1L);
        // 保证两次拿到的是不一样的sqlSession对象
        ConnectSessionUtil.session = null;
        SqlSession session2 = ConnectSessionUtil.getSqlSession();
        // commit()方法，即提交了它的事务，这样我们在第二次查询的时候才会缓存命中，才不会查询数据库，否则就会连着查询两次数据库
        session1.commit();
        session2.getMapper(TestAllDao.class).selectByPrimaryKeyThird(1L);
    }

    /**
     * 修改1或多字段
     */
    @Test
    public void testUpdateNameOrPhone() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("id", 1L);
        requestParams.put("testName", "Jef");
        requestParams.put("testPhone", "Jef");
        boolean updateFlag = testAllDao.updateNameOrPhone(requestParams);
        System.out.println("update flag=" + updateFlag);
        // 事务回滚
        // session.rollback();
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 修改1或多字段
     */
    @Test
    public void testUpdateNameOrPhoneSecond() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAll = new TestAll();
        testAll.setId(1L);
        testAll.setTestName("Jef");
        testAll.setTestPhone("123");
        testAllDao.updateNameOrPhoneSecond(testAll);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 一些关键字的使用
     */
    @Test
    public void testKeywordUse() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("id", 1L);
//        字符串为"0"的处理
        requestParams.put("testName", "0");
        requestParams.put("testPhone", "123456");
        requestParams.put("testPhoneIsOneTwoThree", true);
        // 传入的条件相同，他们的返回结果也是一样的
        List<TestAll> testAlls = testAllDao.selectUseChoose(requestParams);
        List<TestAll> testAllsSecond = testAllDao.selectUseWhere(requestParams);
        List<TestAll> testAllsThird = testAllDao.selectUseTrim(requestParams);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 批量查询
     * 参数为一个list的情况下，collection要设置为list
     */
    @Test
    public void testGetByIds() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<Long> ids = Lists.newArrayList();
        ids.add(1L);
        ids.add(29L);
        List<TestAll> testAlls = testAllDao.selectByIds(ids);
        System.out.println();
    }

    /**
     * 批量查询
     */
    @Test
    public void testGetByMap() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<Long> ids = Lists.newArrayList();
        ids.add(1L);
        ids.add(29L);
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("idList", ids);
        requestParams.put("testName", "001");
        List<TestAll> testAlls = testAllDao.selectByIdsAndOther(requestParams);
        System.out.println();
    }

    /**
     * 批量查询
     */
    @Test
    public void testGetByManyParams() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<Long> ids = Lists.newArrayList();
        ids.add(1L);
        ids.add(29L);
        List<TestAll> testAlls = testAllDao.selectByIdsAndOther("Jef", ids);
        System.out.println();
    }

    /**
     * 通过实体类查询
     */
    @Test
    public void testGetByEntity() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAll = new TestAll();
        testAll.setTestName(BasicConstant.USER_NAME);
        List<TestAll> testAlls = testAllDao.selectByEntity(testAll);
        System.out.println();
    }

    /**
     * 模糊查询
     */
    @Test
    public void testGetByLike() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("testName", "Jef");
        List<TestAll> testAllsForth = testAllDao.selectUseBind(requestParams);
        // 单例测试
        session = ConnectSessionUtil.getSqlSession();
        System.out.println();
    }

    /**
     * 获取二级缓存
     */
    @Test
    public void testGetCache() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        Configuration configuration = session.getConfiguration();
        Collection<Cache> caches = configuration.getCaches();
        System.out.println(caches);
    }

    /**
     * 日志输出
     */
    @Test
    public void testLogOut() {
        System.out.println("test start");
        logger.info("testLog");
        System.out.println("test end");
    }

    /**
     * 根据名称和手机号查询
     */
    @Test
    public void testDeleteByNameAndPhone() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        TestAll testAll = testAllDao.getByNameAndPhone(BasicConstant.USER_NAME, BasicConstant.USER_PHONE);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 根据名称查询
     */
    @Test
    public void testGetByName() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        // 旧版写法开始
//        List<TestAll> testAlls = session.selectOne("com.jef.dao.TestAllDao.getByName", BasicConstant.USER_NAME);
        // 旧版写法结束
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<TestAll> testAlls = testAllDao.getByName(BasicConstant.USER_NAME);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 根据名称查询，不含xml的写法
     */
    @Test
    public void testGetByNameV2() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<TestAll> testAlls = testAllDao.getByNameV2(BasicConstant.USER_NAME);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 根据名称和手机号查询，返回List<Map>
     * 常用于查询的结果无法映射到实体中，用于报表统计之类的
     */
    @Test
    public void testGetByNameMap() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<Map<String, Object>> testAllsMap = testAllDao.getByNameMap(BasicConstant.USER_NAME);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 获取所有的数据
     */
    @Test
    public void testGetAllTestAll() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<TestAll> testAllsMap = testAllDao.getAllTestAll(BasicConstant.USER_NAME);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 根据名称查询存在的数据
     */
    @Test
    public void testGetByNames() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Map<String, Object> params = Maps.newHashMap();
        List<String> list = Lists.newArrayList();
        list.add(BasicConstant.USER_NAME);
        list.add("001");
        list.add("00n");
        params.put("nameList", list);
        params.put("testName", BasicConstant.USER_NAME);
        List<String> allNameList = testAllDao.getByNames(params);
        System.out.println(allNameList.contains("1234"));
        params.put("nameList", Sets.newHashSet(list));
        Set<String> allNameSet = testAllDao.getByNamesSet(params);
        System.out.println(allNameSet.contains("1234"));
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 使用函数求数时容易出NPE处理
     */
    @Test
    public void testGetAllTestNull() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        List<TestAll> testAllsMap = testAllDao.getAllTestAllNPE();
        // 必须remove(null)
        testAllsMap.remove(null);
        for (TestAll testAll : testAllsMap) {
            System.out.println(testAll.getId());
        }
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 获取数量
     */
    @Test
    public void testGetAllTestAllNum() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Integer num = testAllDao.getAllTestAllNum();
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 查询在某个时间点之间新建的数据
     */
    @Test
    public void testGetTestAllByTime() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Date startDate = DateTimeUtil.getFirstDateOfYear(new Date());
        Date endDate  = DateTimeUtil.getNextYear(startDate, 1);
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("startDate", startDate);
        requestParams.put("endDate", endDate);
        List<TestAll> testAlls = testAllDao.getTestAllByTime(requestParams);
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 根据名称查询存在的数据
     */
    @Test
    public void testGetByIdsAndName() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Map<String, Object> params = Maps.newHashMap();
        List<Long> list = Lists.newArrayList();
        list.add(1L);
        list.add(2L);
        List<TestAll> allNameList = testAllDao.getByIdsAndName(list, BasicConstant.USER_NAME);
        System.out.println(allNameList.contains("1234"));
        ConnectSessionUtil.commitAndClose(session);
    }

    /**
     * 查询在某个时间点之间新建的数据
     */
    @Test
    public void testGetTestAllByTimeMap() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        TestAllDao testAllDao = session.getMapper(TestAllDao.class);
        Map<String, Object> resultMap = testAllDao.getTestAllByMap();
        ConnectSessionUtil.commitAndClose(session);
    }



}
