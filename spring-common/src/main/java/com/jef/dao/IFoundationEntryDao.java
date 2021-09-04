package com.jef.dao;

import com.jef.entity.FoundationEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 基金DAO层
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface IFoundationEntryDao {
    /**
     * 新增基金分录信息
     * @param foundationEntry
     * @return
     */
    boolean insert(FoundationEntry foundationEntry);

    /**
     * 根据条件基金
     * @param requestParams
     * @return
     */
    List<FoundationEntry> getByParams(Map<String, Object> requestParams);

    /**
     * 获取最新的基金净值数据
     * @author Jef
     * @date 2020/2/20
     * @return com.jef.entity.FoundationEntry
     */
    FoundationEntry getEntryLastByCode(String code);
    /**
     * 获取最新的基金净值数据
     * @author Jef
     * @date 2020/2/20
     * @return com.jef.entity.FoundationEntry
     */
    FoundationEntry getEntryLastByCodeAndCreate(@Param(value = "code") String code, @Param(value = "createDate") String createDate);

}
