package com.jef.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jef.dao.IFuCaiDao;
import com.jef.entity.FuCai;
import com.jef.service.IFuCaiService;
import com.jef.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2019/7/6
 */
@Service(value = "fucaiService")
public class FuCaiServiceImpl implements IFuCaiService {
   @Autowired
   private IFuCaiDao fuCaiDao;

    @Override
    public FuCai getByDate(String fuDate) {
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("fuDate", fuDate);
        List<FuCai> fuCais = fuCaiDao.getByParams(requestParams);
        if (fuCais != null && fuCais.size() > 0) {
            return fuCais.get(0);
        }
        return null;
    }

    @Override
    public void insert(FuCai fuCai) {
        FuCai fuCaiDb = getByDate(fuCai.getFuDate());
        if (fuCaiDb != null) {
            fuCai.setId(fuCaiDb.getId());
            fuCai.setEditNum(fuCaiDb.getEditNum() + 1);
            fuCaiDao.update(fuCai);
        } else {
            fuCai.setEditNum(0);
            fuCaiDao.insert(fuCai);
        }
    }

    @Override
    public void update(FuCai fuCai) {
        fuCaiDao.update(fuCai);
    }

    @Override
    public Map<String, Integer> getRedDesc() {
        Map<String, Object> requestParams = Maps.newHashMap();
        List<Map<String, String>> redList = Lists.newArrayList();
        redList.addAll(fuCaiDao.getByGroup1(requestParams));
        redList.addAll(fuCaiDao.getByGroup2(requestParams));
        redList.addAll(fuCaiDao.getByGroup3(requestParams));
        redList.addAll(fuCaiDao.getByGroup4(requestParams));
        redList.addAll(fuCaiDao.getByGroup5(requestParams));
        redList.addAll(fuCaiDao.getByGroup6(requestParams));
        Map<String, Integer> redResultMap = Maps.newHashMap();
        for (Map<String, String> redMap : redList) {
            String currentNumber = redMap.get("red");
            String currentNumStr = String.valueOf(redMap.get("numCount"));
            Integer currentNum = Integer.valueOf(currentNumStr);
            if (redResultMap.containsKey(currentNumber)) {
                currentNum = redResultMap.get(currentNumber) + currentNum;
            }
            redResultMap.put(currentNumber, currentNum);
        }
        return MapUtil.sortDescend(redResultMap);
    }

    @Override
    public Map<String, Integer> getBlueDesc() {
        Map<String, Object> requestParams = Maps.newHashMap();
        List<Map<String, String>> blueMapList = fuCaiDao.getByGroupBlue(requestParams);
        Map<String, Integer> blueResultMap = Maps.newHashMap();
        for (Map<String, String> redMap : blueMapList) {
            String currentNumber = redMap.get("blue");
            String currentNum = String.valueOf(redMap.get("numCount"));
            blueResultMap.put(currentNumber, Integer.valueOf(currentNum));
        }
        return MapUtil.sortDescend(blueResultMap);
    }

    @Override
    public FuCai getByGroup(Map<String, Integer> redDescMap, Map<String, Integer> blueDescMap) {
        if (redDescMap == null || redDescMap.size() == 0) {
            redDescMap = getRedDesc();
        }
        if (blueDescMap == null || blueDescMap.size() == 0) {
            blueDescMap = getBlueDesc();
        }
        FuCai fuCai = new FuCai();
        Integer redNum = 1;
        List<String> redNumberList = Lists.newArrayList();
        for (Map.Entry<String, Integer> entry : redDescMap.entrySet()) {
            redNumberList.add(entry.getKey());
            redNum++;
            if (redNum > 6) {
                break;
            }
        }
        Collections.sort(redNumberList);
        fuCai.setRed1(redNumberList.get(0));
        fuCai.setRed2(redNumberList.get(1));
        fuCai.setRed3(redNumberList.get(2));
        fuCai.setRed4(redNumberList.get(3));
        fuCai.setRed5(redNumberList.get(4));
        fuCai.setRed6(redNumberList.get(5));
        for (Map.Entry<String, Integer> entry : blueDescMap.entrySet()) {
            fuCai.setBlue(entry.getKey());
            break;
        }
        return fuCai;
    }

    @Override
    public List<FuCai> getAllFuCai() {
        Map<String, Object> requestParams = Maps.newHashMap();
        return fuCaiDao.getByParams(requestParams);
    }

    public static void main(String[] args) {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("123", 1);
        map.put("23", 3);
        map.put("45", 2);
        Map<String, Integer> map2 = MapUtil.sortDescend(map);
        System.out.println(map2);
    }
}