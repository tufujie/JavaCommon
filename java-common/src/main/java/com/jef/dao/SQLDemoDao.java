package com.jef.dao;

import com.jef.entity.SQLDemo;

import java.util.List;
import java.util.Map;

/**
 * SQL范例demo
 * @author Jef
 * @date 2020/9/29
 */
public interface SQLDemoDao {

    /**
     * 增
     * @author Jef
     * @date 2020/9/29
     * @param
     * @return boolean
     */
    boolean insert(SQLDemo sqlDemo);
    /**
     * 增
     * @author Jef
     * @date 2020/9/29
     * @param
     * @return boolean
     */
    boolean batchInsert(List<SQLDemo> sqlDemoList);

    /**
     * 删
     * @param id
     * @return
     */
    boolean deleteByPrimaryKey(String id);
    /**
     * 删
     * @param idList
     * @return
     */
    boolean batchDeleteByPrimaryKey(List<String> idList);

    /**
     * 改
     * @author Jef
     * @date 2020/9/29
     * @param
     * @return boolean
     */
    boolean updateByPrimaryKeySelective(SQLDemo sqlDemo);

    /**
     * 查
     * @author Jef
     * @date 2020/9/29
     * @param id
     * @return com.jef.entity.SQLDemo
     */
    SQLDemo selectByPrimaryKey(String id);

    /**
     * 查询
     * @author Jef
     * @date 2020/9/29
     * @param requestParams
     * @return List<SQLDemo>
     */
    List<SQLDemo> listSQLDemo(Map<String, Object> requestParams);

}