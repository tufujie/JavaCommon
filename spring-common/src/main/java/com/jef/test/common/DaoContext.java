package com.jef.test.common;

import com.jef.common.context.SpringContextHolder;
import com.jef.dao.IBaseDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储需要引用的Dao
 */
public class DaoContext {

    public static Logger logger = LoggerFactory.getLogger(DaoContext.class);
    /**
     * dao 实例
     */
    private static ConcurrentMap daoInstances = new ConcurrentHashMap();

    public static void put(IBaseDao dao) {
        //取接口名字
        String key = dao.getClass().getInterfaces()[0].getName();
        if (!daoInstances.containsKey(key)) {
            daoInstances.put(key, dao);
        }
    }

    public static void put(String key, IBaseDao dao) {

        //取接口名字
        if (!daoInstances.containsKey(key)) {
            daoInstances.put(key, dao);
        }
    }

    private static <T extends IBaseDao> T getFromClassName(String className) {
        IBaseDao dao = (IBaseDao) daoInstances.get(className);
        if (dao == null) {
            try {
                //使用延迟加载的方式
                Class daoClass = Class.forName(className);
                Object baseDao = SpringContextHolder.getBean(daoClass);
                if (baseDao != null && baseDao instanceof IBaseDao) {
                    daoInstances.put(className, baseDao);
                    dao = (IBaseDao) baseDao;
                }
            } catch (ClassNotFoundException e) {
                logger.error("获取dao失败,{},{}", className, e.getMessage());
                throw new IllegalStateException(className + " is null, please extends  IBaseDao ! ");
            }
        }
        return (T) dao;
    }

    @Deprecated
    public static IBaseDao get(String key) {
        return getFromClassName(key);
    }

    /**
     * @param clazz
     * @return T
     * @Description 范式避免转换错误
     * @author Jef
     * @date 2019/11/25
     */
    public static <T extends IBaseDao> T getInstance(Class<T> clazz) {
        return getFromClassName(clazz.getName());
    }
}
