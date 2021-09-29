package com.jef.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Zookeeper工具类
 *
 * @author Jef
 * @date 2021/9/29
 */
public class ZookeeperUtil {

    /**
     * zookeeper地址
     */
    private static String ZOOKEEPER_URL = "127.0.0.1:2181";

    /**
     * 获取Zookeeper
     *
     * @param
     * @return org.apache.zookeeper.ZooKeeper
     * @author Jef
     * @date 2021/9/29
     */
    public static ZooKeeper getZookeeper() throws IOException {
        return new ZooKeeper(ZOOKEEPER_URL, 5000, new ZookeeperWatcher());
    }

    /**
     * 获取Zookeeper
     *
     * @param
     * @return org.apache.zookeeper.ZooKeeper
     * @author Jef
     * @date 2021/9/29
     */
    public static ZooKeeper getZookeeper(long sessionId, byte[] sessionPasswd) throws IOException {
        return new ZooKeeper(ZOOKEEPER_URL, 5000, new ZookeeperWatcher(), sessionId, sessionPasswd);
    }

    /**
     * 打印状态
     *
     * @param zooKeeper
     * @return void
     * @author Jef
     * @date 2021/9/29
     */
    public static void printState(ZooKeeper zooKeeper) {
        System.out.println("zookeeper state is " + zooKeeper.getState());
    }

}