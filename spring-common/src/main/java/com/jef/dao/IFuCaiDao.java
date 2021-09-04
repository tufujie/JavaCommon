package com.jef.dao;

import com.jef.entity.FuCai;
import java.util.List;
import java.util.Map;

/**
 * 福彩DAO层
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface IFuCaiDao {
    /**
     * 根据ID查询福彩信息
     * @param id
     * @return
     */
    FuCai selectByPrimaryKey(Long id);

    /**
     * 根据条件福彩
     * @param requestParams
     * @return
     */
    List<FuCai> getByParams(Map<String, Object> requestParams);

    /**
     * 新增福彩信息
     * @param fuCai
     * @return
     */
    boolean insert(FuCai fuCai);
    /**
     * 更新福彩信息
     * @param fuCai
     * @return
     */
    boolean update(FuCai fuCai);

    List<Map<String, String>> getByGroup1(Map<String, Object> requestParams);
    List<Map<String, String>> getByGroup2(Map<String, Object> requestParams);
    List<Map<String, String>> getByGroup3(Map<String, Object> requestParams);
    List<Map<String, String>> getByGroup4(Map<String, Object> requestParams);
    List< Map<String, String>> getByGroup5(Map<String, Object> requestParams);
    List<Map<String, String>> getByGroup6(Map<String, Object> requestParams);
    List<Map<String, String>> getByGroupBlue(Map<String, Object> requestParams);

}
