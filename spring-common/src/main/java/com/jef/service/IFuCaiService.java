package com.jef.service;

import com.jef.entity.FuCai;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2019/7/6
 */
public interface IFuCaiService {

    FuCai getByDate(String fullDate);

    /**
     * 新增
     * @param fucai
     */
    void insert(FuCai fucai);

    /**
     * 修改
     * @param fucai
     */
    void update(FuCai fucai);

    /**
     * 红球排序
     * @return
     */
    Map<String, Integer> getRedDesc();

    /**
     * 蓝球排序
     * @return
     */
    Map<String, Integer> getBlueDesc();

    /**
     * 获取推荐号码
     * @param redDescMap
     * @param blueDescMap
     * @return
     */
    FuCai getByGroup(Map<String, Integer> redDescMap, Map<String, Integer> blueDescMap);

    /**
     * 获取所有的福彩
     * @return
     */
    List<FuCai> getAllFuCai();
}