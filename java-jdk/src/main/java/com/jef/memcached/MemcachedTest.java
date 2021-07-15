package com.jef.memcached;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Memcached使用测试
 * 设置缓存，获取缓存
 * 可以形成工具类，封装进行调用
 * @author Jef
 * @date 2019/4/25
 */
public class MemcachedTest {
    private static MemcachedClient mcc;

    public static void main(String[] args) {
        init();
        mecachedSet();
        mecachedAdd();
        memcachedReplace();
        memcachedAppend();
        memcachedPrepend();
        memcachedCAS();
        memcachedGet();
        memcachedDelete();
        memcachedIncrOrDecr();
        shutDown();
    }

    /**
     * 通过memcached提供的MemcachedClient类我们可以创建一个连接并能很轻松的使用这个高效的内存数据库
     * 初始化连接
     */
    public static void init() {
        try {
            mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("connect server success!");
    }

    public static void shutDown() {
        mcc.shutdown();
    }

    /**
     * 设置缓存值
     * @author Jef
     * @date 2019/4/25
     * @param
     * @return void
     */
    public static void mecachedSet() {
        // 存储数据
        Future fo = mcc.set("name", 900, "Jef");

        // 查看存储状态
        try {
            System.out.println("set status:" + fo.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 输出值
        System.out.println("name value in cache - " + mcc.get("name"));
    }

    public static void mecachedAdd() {
        // 添加数据
        Future f1 = mcc.set("phone", 900, "18390220001");

        // 打印状态
        try {
            System.out.println("set status:" + f1.get());
            // 输出
            System.out.println("phone value in cache - " + mcc.get("phone"));

            // 添加
            Future f2 = mcc.add("age", 900, 20);

            // 打印状态
            System.out.println("add status:" + f2.get());
            // 添加新key
            f2 = mcc.add("height", 900, 176);

            // 打印状态
            System.out.println("add status:" + f2.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 输出
        System.out.println("phone value in cache - " + mcc.get("phone"));
        System.out.println("height value in cache - " + mcc.get("height"));
    }

    public static void memcachedReplace() {
        // 添加第一个 key=》value 对
        Future fo = mcc.set("phone", 900, "18390220002");

        // 输出执行 add 方法后的状态
        try {
            System.out.println("add status:" + fo.get());
            // 获取键对应的值
            System.out.println("phone value in cache - " + mcc.get("phone"));

            // 添加新的 key
            fo = mcc.replace("phone", 900, "18390220003");

            // 输出执行 set 方法后的状态
            System.out.println("replace status:" + fo.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 获取键对应的值
        System.out.println("phone value in cache - " + mcc.get("phone"));
    }

    public static void memcachedAppend() {
        // 添加数据
        Future f1 = mcc.set("Jef'description", 900, "good");
        try {
            // 输出执行 set 方法后的状态
            System.out.println("set status:" + f1.get());

            // 获取键对应的值
            System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));

            // 对存在的key进行数据添加操作
            Future f2 = mcc.append("Jef'description", ",very good");

            // 输出执行 set 方法后的状态
            System.out.println("append status:" + f2.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 获取键对应的值
        System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));
    }

    public static void memcachedPrepend() {
        // 添加数据
        Future f1 = mcc.set("Jef'description", 900, "good");
        try {
            // 输出执行 set 方法后的状态
            System.out.println("set status:" + f1.get());

            // 获取键对应的值
            System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));

            // 对存在的key进行数据添加操作
            Future f2 = mcc.prepend("Jef'description", ",very good");

            // 输出执行 set 方法后的状态
            System.out.println("prepend status:" + f2.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 获取键对应的值
        System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));
    }

    public static void memcachedCAS() {
        // 添加数据
        Future fo = mcc.set("Jef'description", 900, "good");
        try {
            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 使用 get 方法获取数据
            System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));

            // 通过 gets 方法获取 CAS token（令牌）
            CASValue casValue = mcc.gets("Jef'description");

            // 输出 CAS token（令牌） 值
            System.out.println("CAS token - " + casValue);

            // 尝试使用cas方法来更新数据
            CASResponse casresp = mcc.cas("Jef'description", casValue.getCas(), 900, "vary good");

            // 输出 CAS 响应信息
            System.out.println("CAS Response - " + casresp);
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 输出值
        System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));
    }

    /**
     * 获取缓存值
     * @author Jef
     * @date 2019/4/25
     * @param
     * @return void
     */
    public static void memcachedGet() {
        // 添加数据
        Future fo = mcc.set("Jef'description", 900, "good");
        try {
            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 从缓存中获取键为 expample6 的值
            System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));

            // 通过 gets 方法获取 CAS token（令牌）
            CASValue casValue = mcc.gets("Jef'description");
            // 输出 CAS token（令牌） 值
            System.out.println("CAS value in cache - " + casValue);
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void memcachedDelete() {
        // 添加数据
        Future f1 = mcc.set("Jef'description", 900, "good");
        try {
            // 输出执行 set 方法后的状态
            System.out.println("set status:" + f1.get());

            // 获取键对应的值
            System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));

            // 对存在的key进行数据添加操作
            Future f2 = mcc.delete("Jef'description");

            // 输出执行 delete 方法后的状态
            System.out.println("delete status:" + f2.get());

            // 获取键对应的值
            System.out.println("Jef'description value in cache - " + mcc.get("Jef'description"));
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void memcachedIncrOrDecr() {
        // 添加数字值
        Future fo = mcc.set("number", 900, "1000");
        try {
            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 获取键对应的值
            System.out.println("value in cache - " + mcc.get("number"));

            // 自增并输出
            System.out.println("value in cache after increment - " + mcc.incr("number", 111));

            // 自减并输出
            System.out.println("value in cache after decrement - " + mcc.decr("number", 112));
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
