package com.jef.designpattern.structure.flyweight.one;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 安全管理，实现成单例
 *
 * @author Jef
 * @date 2019/2/21
 */
public class SecurityMgr {
    private static SecurityMgr securityMgr = new SecurityMgr();
    private SecurityMgr() {

    }
    public static SecurityMgr getInstence() {
        return securityMgr;
    }

    /**
     * 在运行期间，用来存放登录人员对应的权限
     * 在Web应用中，这些数据通常会存放到session中
     */
    private Map<String, Collection<FlyWeight>> map = new HashMap<String, Collection<FlyWeight>>();

    /**
     * 模拟登录的功能
     * @param user 登录的用户
     */
    public void login(String user) {
        // 登录时就需要把该用户所拥有的权限，从数据库中取出来，放到缓存中去
        Collection<FlyWeight> col = queryByUser(user);
        map.put(user, col);
    }

    /**
     * 判断某用户对某个安全实体时候拥有某种权限
     * @param user 被检测权限的用户
     * @param securityEntity 安全实体
     * @param permit 权限
     */
    public boolean hasPermit(String user, String securityEntity, String permit) {
        Collection<FlyWeight> col = map.get(user);
        if (col == null || col.size() == 0) {
            System.out.println(user + "没有登录或者没有被分配任何权限");
            return false;
        }
        for (FlyWeight fm : col) {
            // 输出当前实例，看看是否同一个实例对象
            System.out.println("fm==" + fm);
            if (fm.match(securityEntity, permit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从数据库中获取某人所拥有的权限
     * @param user 需要获取所拥有的权限的用户
     * @return 某人所拥有的权限
     */
    private Collection<FlyWeight> queryByUser(String user) {
        Collection<FlyWeight> col = new ArrayList<FlyWeight>();
        for (String s : TestDB.colDB) {
            String[] ss = s.split(",");
            if (ss[0].equals(user)) {
                FlyWeight fm = FlyweightFactory.getInstance().getFlyweight(ss[1] + "," + ss[2]);
                col.add(fm);
            }
        }
        return col;
    }

}