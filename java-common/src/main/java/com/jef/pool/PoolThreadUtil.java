package com.jef.pool;

import com.google.common.collect.Maps;
import com.jef.constant.BasicList;
import com.jef.entity.User;
import com.jef.util.CounterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

/**
 * 后台轮询
 *
 * @author Jef
 * @date 2019/8/20
 */
public class PoolThreadUtil {
    private static final Logger logger = LoggerFactory.getLogger(PoolThreadUtil.class);
    private static final String CACHE_ID = "cacheID";

    public static void main(String[] args) throws InterruptedException {
        new PoolThreadUtil().pool();
    }

    /**
     * 轮询方法
     */
    public void pool() throws InterruptedException {
        List<User> userList =  BasicList.getUserList();
        Map<String, Integer> map = Maps.newHashMap();
        // 轮询次数，目前1秒1次，大概10秒
        int poolCount = 10;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < poolCount; i++) {
            dealTask(userList, map);
            if (map.get(CACHE_ID) == userList.size() - 1) {
                System.out.println("任务执行完毕");
                break;
            }
            Thread.sleep(1000);
        }
        stopWatch.stop();
        System.out.println("执行任务花费时长=" +  stopWatch.getTotalTimeSeconds() + "s");

    }

    /**
     * 处理任务
     */
    public void dealTask(List<User> userList, Map<String, Integer> map) throws InterruptedException {
        for (int j = 0; j < userList.size(); j++) {
            System.out.println("j=" + j);
            map.put(CACHE_ID, j);
            Thread.sleep(1000);
        }
    }

    /**
     * 是否已完成
     * @author Jef
     * @date 2019/8/20
     * @param
     * @return boolean
     */
    public boolean getFinished(int listSize, CounterUtil counterUtil) throws InterruptedException {
        System.out.println("执行任务");
        return counterUtil.addAndGet() == listSize;
    }



}