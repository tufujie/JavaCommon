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
    private static final String ZOOKEEPER_URL = "127.0.0.1:2181";
    /**
     * 用户名密码
     */
    private static final String USER_NAME_PASS = "jef:123456";
    /**
     * 用户名密码加密
     */
    public static final String USER_NAME_PASS_DIGEST = "jef:otDVfHKK4YU8a0pm8GMo3/0olPA=";
    /**
     * NODE1
     */
    public static final String NODE_1 = "/node1";
    /**
     * NODE1
     */
    public static final String NODE_1_CHILDREN_1 = "/node1/children1";
    /**
     * 数据
     */
    public static final String DATA = "hello world";

    /**
     * 获取Zookeeper
     *
     * @param
     * @return org.apache.zookeeper.ZooKeeper
     * @author Jef
     * @date 2021/9/29
     */
    public static ZooKeeper getZookeeper() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_URL, 5000, new ZookeeperWatcher());
        /**
         * 设置ACL
         * 先注册一个用户名 jef 密码为 123456 的用户
         * 因为有些时候创建节点时添加了 digest ACL 所以在获取该节点信息的时候,要先添加授权
         */
        zooKeeper.addAuthInfo("digest", ZookeeperUtil.USER_NAME_PASS.getBytes());
        return zooKeeper;
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
        System.out.println("Zookeeper state is 【" + zooKeeper.getState() + "】");
    }

    /**
     * 打印数据
     *
     * @param zooKeeper
     * @return void
     * @author Jef
     * @date 2021/9/29
     */
    public static void printData(byte[] data) {
        System.out.println("Zookeeper data is 【" + new String(data) + "】");
    }

}