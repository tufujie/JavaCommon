package com.jef.dao;

import com.jef.entity.Foundation;
import com.jef.entity.FoundationBuy;
import com.jef.entity.FoundationShop;

import java.util.List;
import java.util.Map;

/**
 * 基金DAO层
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface IFoundationDao {
    /**
     * 新增基金信息
     * @param foundation
     * @return
     */
    boolean insert(Foundation foundation);

    /**
     * 根据ID查询基金信息
     * @param code 基金编码
     * @return
     */
    Foundation selectByPrimaryKey(String code);

    /**
     * 根据条件基金
     * @param requestParams
     * @return
     */
    List<Foundation> getByParams(Map<String, Object> requestParams);

    /**
     * 获取卖出记录
     * @author Jef
     * @date 2020/2/29
     * @param requestParams
     * @return java.util.List<com.jef.entity.FoundationShop>
     */
    List<FoundationShop> getShopByParams(Map<String,Object> requestParams);
    /**
     * 获取卖出记录
     * @author Jef
     * @date 2020/2/29
     * @param id
     * @return java.util.List<com.jef.entity.FoundationShop>
     */
    FoundationShop getShopByID(String id);

    /**
     * 获取买入记录
     * @author Jef
     * @date 2020/2/29
     * @param requestParams
     * @return java.util.List<com.jef.entity.FoundationShop>
     */
    List<FoundationBuy> getBuyByParams(Map<String, Object> requestParams);

    /**
     * 获取买入记录
     * @author Jef
     * @date 2020/2/29
     * @param id
     */
    FoundationBuy getBuyByID(String id);

    boolean insertBuy(FoundationBuy buy);

    boolean insertShop(FoundationShop shop);
}
