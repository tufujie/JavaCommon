package com.jef.dao;

import com.jef.entity.TestAll;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 能查即能改、删，尽量做查询测试
 * dao的请求方式
 * 1、一个参数（Object(基本类型，实体对象，List，Map）
 * 2、多个参数，利用org.apache.ibatis.annotations.Param;
 * @author Jef
 */
public interface TestAllDao {

    /**
     * 批量查询，传入实体类
     * 类似于放置map请求
     * @param testAll testAll对象
     * @return
     */
    List<TestAll> selectByEntity(TestAll testAll);

    /**
     * 批量插入，数据量比较大的情况下可以提供返回值
     * @author Jef
     * @date 2019/8/5
     * @param tests
     * @return boolean
     */
    boolean batchInsert(List<TestAll> tests);

    /**
     * 批量查询，map
     * 参数为一个map的时候，map的key需要对应xml的值
     * 等效于上面的查询
     * @param requestParams 请求条件
     * @return
     */
    List<TestAll> selectByIdsAndOther(Map<String, Object> requestParams);

    /**
     * 批量查询，多个参数
     * 参数为多个参数的时候，@Param对应的value需要对应xml的值，类似于放置map请求
     * 必须添加@Param注解
     * 可以不用添加 parameterType
     * @param testName 测试名称
     * @param ids ids
     * @return
     */
    List<TestAll> selectByIdsAndOther(@Param("testName") String testName, @Param("idList") List<Long> ids);

   /**
    * 单个插入
    * @author Jef
    * @date 2019/8/5
    * @param test Test对象
    * @return boolean
    */
    boolean insertSelect(TestAll test);
    /**
     * 单个插入
     * @author Jef
     * @date 2019/8/5
     * @param test Test对象
     * @return boolean
     */
    int insert(TestAll test);

    /**
     * 根据id集合删除，实际操作中需要判断集合不为null和集合大小不为0
     * @param ids
     * @return
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 根据ID批量更新字段
     * @param requestParams 请求条件
     * @return boolean
     */
    boolean updateNameOrPhone(Map<String, Object> requestParams);

    /**
     * 根据ID批量更新字段
     * @param testAll
     * @return boolean
     */
    boolean updateNameOrPhoneSecond(TestAll testAll);

    /**
     * 下面为各种形式的查询
     * 查询的条件格式可以作为更新、删除的条件格式
     */

    // 一些关键字的使用，考虑一个人有多个手机号码
    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param requestParams 请求条件
     * @return java.util.List<com.jef.entity.TestAll>
     */
    List<TestAll> selectUseChoose(Map<String, Object> requestParams);
    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param requestParams 请求条件
     * @return java.util.List<com.jef.entity.TestAll>
     */
    List<TestAll> selectUseWhere(Map<String, Object> requestParams);
    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param requestParams 请求条件
     * @return java.util.List<com.jef.entity.TestAll>
     */
    List<TestAll> selectUseTrim(Map<String, Object> requestParams);
    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param requestParams 请求条件
     * @return java.util.List<com.jef.entity.TestAll>
     */
    List<TestAll> selectUseBind(Map<String, Object> requestParams);

    /**
     * 单个查询
     * @param id
     * @return
     */
    TestAll selectByPrimaryKey(Long id);

    /**
     * 单个查询
     * @param id
     * @return
     */
    TestAll selectByPrimaryKeySecond(Long id);

    /**
     * 单个查询
     * @param id
     * @return
     */
    TestAll selectByPrimaryKeyThird(Long id);

    /**
     * 批量查询
     * 这里如果不使用@Param的话相当于没有为集合重命名，则collection="list"
     * @param ids
     * @return
     */
    List<TestAll> selectByIds(@Param("idList") List<Long> ids);

    /**
     * 批量查询，可以达到放置map的效果，条件为id集合或者是条件少的时候可以使用
     * @param ids
     * @param name
     * @return
     */
    List<TestAll> getByIdsAndName(@Param("idList") List<Long> ids, @Param("name") String name);

    /**
     * 根据名称和电话查询
     * @author Jef
     * @date 2019/8/5
     * @param testName
     * @param testPhone
     * @return com.jef.entity.TestAll
     */
    TestAll getByNameAndPhone(@Param("testName") String testName, @Param("testPhone") String testPhone);

    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param testName
     * @return java.util.List<com.jef.entity.TestAll>
     */
    List<TestAll> getByName(String testName);
    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param testName
     * @return java.util.List<com.jef.entity.TestAll>
     */
    @Select("select id, test_name testName, test_phone testPhone from test_all where test_name = #{testName}")
    List<TestAll> getByNameV2(String testName);
    /**
     * 查询
     * @author Jef
     * @date 2019/8/5
     * @param testName
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> getByNameMap(String testName);
    /**
     * 功能描述
     * @author Jef
     * @date 2019/8/5
     * @param testName
     * @return java.util.List<com.jef.entity.TestAll>
     */
    List<TestAll> getAllTestAll(String testName);
    /**
     * 功能描述
     * @author Jef
     * @date 2019/8/5
     * @param params
     * @return java.util.List<java.lang.String>
     */
    List<String> getByNames(Map<String, Object> params);

    /**
     * SQL跟getByName完全一样，跟getByNames除了方法名和返回结果类型不一样之外，传参可以完全一样（也可以传Set）
     * @param params
     * @return
     */
    Set<String> getByNamesSet(Map<String, Object> params);

    /**
     * 导致NPE的查询集合
     * @return
     */
    List<TestAll> getAllTestAllNPE();

    /**
     * 获取数据条数
     * @return 数据条数
     */
    Integer getAllTestAllNum();

   /**
    * 查询在某个时间点之间新建的数据
    * createTime >= #{createTime}
    * createTime &gt;= #{createTime}
    * <![CDATA[ createTime >= #{createTime} ]]>
    * @author Jef
    * @date 2019/8/5
    * @param requestParams
    * @return java.util.List<com.jef.entity.TestAll>
    */
    List<TestAll> getTestAllByTime(Map<String, Object> requestParams);

   /**
    * 获取所有信息
    * @author Jef
    * @date 2019/8/5
    * @param
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    Map<String, Object> getTestAllByMap();

}
