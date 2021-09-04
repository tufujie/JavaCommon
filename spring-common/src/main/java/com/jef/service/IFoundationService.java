package com.jef.service;

import com.jef.entity.Foundation;
import com.jef.entity.FoundationBuy;
import com.jef.entity.FoundationEntry;
import com.jef.entity.FoundationShop;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2019/7/6
 */
public interface IFoundationService {


    /**
     * 新增
     * @param foundation
     */
    void insert(Foundation foundation);

    /**
     * 获取所有的基金
     * @return
     */
    List<Foundation> getByParams(Map<String, Object> requestParams);
    /**
     * 新增
     * @param foundationEntry
     */
    void insertEntry(FoundationEntry foundationEntry);

    /**
     * 获取所有的基金
     * @return
     */
    List<FoundationEntry> getEntryByParams(Map<String, Object> requestParams);
    /**
     * 获取最新的基金
     * @return
     */
    FoundationEntry getEntryLastByCode(String code);
    /**
     * 获取唯一净值
     * @return
     */
    FoundationEntry getEntryByCodeAndCreate(String code, String createDate);

    /**
     * 根据编码获取基金
     * @return
     */
    Foundation getByCode(String code);

    /**
     * 3个月数据是否可以购买
     * @return
     */
    boolean getByFlag(String code, Integer day);
    /**
     * 是否最新数据
     * @return
     */
    boolean getNewest(String code) throws ParseException;

    List<FoundationShop> getShopByParams(Map<String, Object> requestParams);

    FoundationShop getShopByID(String id);

    List<FoundationBuy> getBuyByParams(Map<String, Object> requestParams);

    FoundationBuy getBuyByID(String id);

    void insertBuy(FoundationBuy buy);

    void insertShop(FoundationShop shop);
}