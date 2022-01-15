package com.jef.dao;

import java.util.List;
import java.util.Map;

/**
 * 工作业务DAO层
 *
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface IWorkBusinessDao extends IBaseDao {

    List<Map<String, Object>> getAllUser();

    List<Map<String, Object>> getContract();

    List<Map<String, Object>> getContractType();

    List<Map<String, Object>> getDisbursementType();

}
