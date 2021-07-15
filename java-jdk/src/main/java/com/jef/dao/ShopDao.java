package com.jef.dao;

import com.jef.entity.Shop;

/**
 * 店铺DAO
 */
public interface ShopDao {
    /**
     * 根据ID查询店铺信息
     * @param id
     * @return
     */
    Shop selectByPrimaryKey(Long id);

}
