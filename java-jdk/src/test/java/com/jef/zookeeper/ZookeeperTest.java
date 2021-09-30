package com.jef.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * ZookeeperTest
 *
 * @author Jef
 * @date 2021/9/29
 */
public class ZookeeperTest {

    /**
     * 测试打印状态
     */
    @Test
    public void testGetZookeeperState() throws Exception {
        //===============================创建会话======================================

        /**
         * 使用第一个构造器创建会话,刚创建完立刻打印会话状态为 CONNECTING
         * 线程阻塞5秒,这5秒期间收到了服务端的Watcher通知 SyncConnected
         * 之后会话状态为 CONNECTED
         */
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        ZookeeperUtil.printState(zookeeperObj);
        Thread.sleep(2000);
        ZookeeperUtil.printState(zookeeperObj);
        /**
         * 使用第三个构造器，sessionId 和 sessionPasswd用的是上一个连接
         */
        long sessionId = zookeeperObj.getSessionId();
        byte[] sessionPasswd = zookeeperObj.getSessionPasswd();
        ZooKeeper zookeeperObj2 = ZookeeperUtil.getZookeeper(sessionId, sessionPasswd);
        ZookeeperUtil.printState(zookeeperObj2);
        Thread.sleep(2000);
        ZookeeperUtil.printState(zookeeperObj2);
    }

    @Test
    public void testCreatePersistentNode() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        /**
         * 异步创建一个持久节点, ACL为 digest:ZookeeperUtil.USER_NAME_PASS_DIGEST:cdrwa,等同于如下命令：
         * create /node1 123 digest:ZookeeperUtil.USER_NAME_PASS_DIGEST:cdrwa
         */
        zookeeperObj.create(ZookeeperUtil.NODE_1,
                ZookeeperUtil.DATA.getBytes(),
                Collections.singletonList(new ACL(ZooDefs.Perms.ALL, new Id("digest", ZookeeperUtil.USER_NAME_PASS_DIGEST))),
                CreateMode.PERSISTENT,
                new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("rc:" + rc);
                        System.out.println("path:" + path);
                        System.out.println("ctx:" + ctx);
                        System.out.println("name:" + name);
                    }
                }, "传给服务端的内容,会在异步回调时传回来");
        /**
         * 注意这里,线程睡眠20秒,可以接收到watcher
         */
        Thread.sleep(20000);
    }

    /**
     * 测试创建节点
     *
     * @date 2021/9/29
     */
    @Test
    public void testCreatePersistentNodeV2() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        //===============================创建节点======================================
        /**
         * CreateMode
         *    PERSISTENT : 持久节点
         *    PERSISTENT_SEQUENTIAL : 持久顺序节点
         *    EPHEMERAL : 临时节点
         *    EPHEMERAL_SEQUENTIAL : 临时顺序节点
         *
         * 无论是同步还是异步接口，ZooKeeper都不支持递归创建，即无法在父节点不存在的情况下创建一个子节点。
         * 另外，如果一个节点已经存在了，那么创建同名节点的时候，会抛出NodeExistException异常。如果是顺序节点,那么永远不会抛出NodeExistException异常
         * 临时节点不能有子节点,创建节点时,如果给定的父节点是临时节点,则会抛出NoChildrenForEphemeralsException
         * 创建节点同时，也可以在节点上设置Watcher 当删除节点或者setData时，将会触发Watcher
         */
        /**
         * 同步创建一个持久节点,ACL为 world:anyone:cdrwa 等同于如下命令：
         * create /node 123 world:anyone:cdrwa
         */
        String nodePath = "/node2";
        //===============================判断节点是否存在======================================

        /**
         * 返回给定路径的节点的stat。如果不存在这样的节点，则返回null。
         * 如果wathher非空并且调用成功（不会抛出异常），则会在具有给定路径的节点上保留监视。wather将由创建/删除节点或在节点上设置数据的成功时触发。
         */

        /**
         * 同步检查节点是否存在,并留下监听
         */
        Stat exists = zookeeperObj.exists(nodePath, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("留下监视");
            }
        });
        System.out.println("判断节点是否存在：" + exists);

        Stat stat = zookeeperObj.exists(nodePath, true);
        if (stat == null) {
            zookeeperObj.create(nodePath, ZookeeperUtil.DATA.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        /**
         * 同步创建一个持久节点，ACL为 world:anyone:cdrwa 所有人只拥有创建的权限,等同于如下命令：
         * create /node3 123 world:anyone:c
         */
        String nodePath2 = "/node3";
        /**
         * 异步检查节点是否存在,并留下监听
         */
        zookeeperObj.exists(nodePath2, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("留下监视");
            }
        }, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                System.out.println("rc:" + rc);
                System.out.println("path:" + path);
                System.out.println("ctx:" + ctx);
                System.out.println("判断节点是否存在：" + stat);

            }
        }, "传给服务端的内容,会在异步回调时传回来");
        if (stat == null) {
            zookeeperObj.create(nodePath2, ZookeeperUtil.DATA.getBytes(), Collections.singletonList(new ACL(ZooDefs.Perms.CREATE, ZooDefs.Ids.ANYONE_ID_UNSAFE)), CreateMode.PERSISTENT);
        }
    }

    @Test
    public void testCreatePersistentSequentialNod() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        /**
         * 创建一个持久顺序定时节点，如果在10000毫秒内 未修改node，并且没有子节点,那么它将被删掉
         */
        zookeeperObj.create("/node5",
                ZookeeperUtil.DATA.getBytes(),
                Collections.singletonList(new ACL(ZooDefs.Perms.ALL, new Id("digest", ZookeeperUtil.USER_NAME_PASS_DIGEST))),
                CreateMode.PERSISTENT_SEQUENTIAL_WITH_TTL,
                new AsyncCallback.Create2Callback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name, Stat stat) {
                        System.out.println("rc:" + rc);
                        System.out.println("path:" + path);
                        System.out.println("ctx:" + ctx);
                        System.out.println("name:" + name);
                        System.out.println("stat:" + stat);
                    }
                }, "传给服务端的内容,会在异步回调时传回来", 10000);
        Thread.sleep(20000);
    }


    /**
     * 测试临时节点
     *
     * @author Jef
     * @date 2021/9/29
     */
    @Test
    public void testCreateEphemeralNode() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();

        /**
         * 异步创建一个 临时的顺序节点，ACL为 ip:127.0.0.1:c 等同于如下命令：
         * create /node4 123 ip:127.0.0.1:c
         */
        zookeeperObj.create("/node4",
                ZookeeperUtil.DATA.getBytes(),
                Collections.singletonList(new ACL(ZooDefs.Perms.CREATE, new Id("ip", "127.0.0.1"))),
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("rc:" + rc);
                        System.out.println("path:" + path);
                        System.out.println("ctx:" + ctx);
                        System.out.println("name:" + name);
                    }
                }, "传给服务端的内容,会在异步回调时传回来");
        /**
         * 注意这里,线程睡眠20秒,因为是创建的临时节点,如果不睡眠,你不能使用命令在控制台看见创建的临时节点
         */
        Thread.sleep(20000);
    }

    @Test
    public void testCreateEphemeralSequentialNode() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        /**
         * 异步创建一个 临时的顺序节点，ACL为 ip:127.0.0.1:c 等同于如下命令：
         * create /node4 123 ip:127.0.0.1:c
         */
        zookeeperObj.create("/node4",
                ZookeeperUtil.DATA.getBytes(),
                Collections.singletonList(new ACL(ZooDefs.Perms.CREATE, new Id("ip", "127.0.0.1"))),
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("rc:" + rc);
                        System.out.println("path:" + path);
                        System.out.println("ctx:" + ctx);
                        System.out.println("name:" + name);
                    }
                }, "传给服务端的内容,会在异步回调时传回来");
        /**
         * 注意这里,线程睡眠20秒,因为是创建的临时节点,如果不睡眠,你不能使用命令在控制台看见创建的临时节点
         */
        Thread.sleep(20000);
    }

    @Test
    public void testDeleteNode() throws Exception {
        //===============================删除节点======================================

        /**
         * 删除给定路径的节点。如果存在这样的节点，则调用将成功，并且给定版本与节点的版本匹配（如果给定版本为-1，则它匹配任何节点的版本）。
         * 如果节点不存在，将抛出NoNodeException。
         * 如果给定版本与节点的版本不匹配，将抛出BadVersionException。
         * 如果节点有子节点，将抛出NotEmptyException。
         * 如果成功将触发现有API调用留下的给定路径节点上的所有监视，以及getChildren API调用留下的父节点上的监视。
         */
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        /**
         * 同步删除节点
         * path 节点路径
         * version 版本号 -1 代表匹配所有版本
         */
        zookeeperObj.delete(ZookeeperUtil.NODE_1, -1);

        /**
         * 异步删除节点
         */
        zookeeperObj.delete("/node4", -1, new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                System.out.println("rc:" + rc);
                System.out.println("path:" + path);
                System.out.println("ctx:" + ctx);
            }
        }, "传给服务端的内容,会在异步回调时传回来");
        Thread.sleep(2000);
    }

    @Test
    public void testSetNodeData() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        //===============================修改节点数据======================================

        /**
         * setData节点有version这一参数，给定版本与节点的版本匹配，则设置给定路径的节点的数据（如果给定版本是-1，则它匹配任何节点的版本）。返回节点的统计信息。
         * 如果不存在具有给定路径的节点，则将抛出NoNodeException
         * 如果给定版本与节点的版本不匹配，将抛出BadVersionException
         * 设置的数据最大允许大小为1 MB
         */

        /**
         * 同步设置数据 -1匹配任何版本
         */
        Stat stat = zookeeperObj.setData(ZookeeperUtil.NODE_1, "嗨喽".getBytes(), -1);
        System.out.println(stat);

        /**
         * 异步设置数据
         */
        zookeeperObj.setData(ZookeeperUtil.NODE_1, "helloword".getBytes(), -1, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {

                System.out.println("rc:" + rc);
                System.out.println("path:" + path);
                System.out.println("ctx:" + ctx);
                System.out.println("stat:" + stat);

            }
        }, "传给服务端的内容,会在异步回调时传回来");
        Thread.sleep(2000);
    }


    @Test
    public void testGetNodeData() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
//===============================获取节点数据======================================
        /**
         * 同步调用
         * path 节点路径
         * Watcher 监视
         * Stat 节点统计信息
         * 添加授权之后同步获取节点信息，返回给定路径的节点的数据和统计信息
         * 如果调用成功,并且watcher参数不为空,则会在具有给定路径的节点上保留监视，当删除节点 或者 setData时候将会触发监视
         *
         */
        byte[] data = zookeeperObj.getData(ZookeeperUtil.NODE_1, new ZookeeperWatcher(), new Stat());
        ZookeeperUtil.printData(data);
        /**
         * 注意这里,线程睡眠2秒,因为是创建的临时节点,如果不睡眠,你不能使用命令在控制台看见创建的临时节点
         */
        Thread.sleep(2000);
    }

    @Test
    public void testGetNodeDataV2() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        /**
         * 异步调用
         * path 节点路径
         * watch true使用创建zookeeper时指定的默认watcher 如果为false则不设置监听
         * DataCallback 异步通知
         * ctx 回调上下文
         */
        zookeeperObj.getData(ZookeeperUtil.NODE_1, false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("rc:" + rc);
                System.out.println("path:" + path);
                System.out.println("ctx:" + ctx);
                System.out.println("data:" + new String(data));
                System.out.println("stat:" + stat);
            }
        }, "传给服务端的内容,会在异步回调时传回来");
        Thread.sleep(2000);
    }

    @Test
    public void testGetChildren() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        List<String> children = zookeeperObj.getChildren(ZookeeperUtil.NODE_1, new ZookeeperWatcher(), new Stat());
        System.out.println(children);
    }

    @Test
    public void testSetGetACL() throws Exception {
        ZooKeeper zookeeperObj = ZookeeperUtil.getZookeeper();
        //===============================ACL操作======================================
        /**
         * ACL操作只给 同步  ，异步 自己看文档
         */
        Stat auth = zookeeperObj.setACL(ZookeeperUtil.NODE_1, Collections.singletonList(new ACL(ZooDefs.Perms.ALL, new Id("auth", "jef:123456"))), -1);
        System.out.println(auth);
        /**
         * 获取ACL
         */
        List<ACL> acl = zookeeperObj.getACL(ZookeeperUtil.NODE_1, new Stat());
        System.out.println(acl);


    }

}
