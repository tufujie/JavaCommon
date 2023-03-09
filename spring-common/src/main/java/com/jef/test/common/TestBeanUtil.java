package com.jef.test.common;

import cn.hutool.core.bean.BeanException;
import com.jef.dao.IBaseDao;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestBeanUtil {
    private static TestApplicationContext context = null;

    public static TestApplicationContext getTestApplicationContext() {
        if (context == null) {
            context = new TestApplicationContext();
        }
        return context;
    }

    public static Object getApplicationBean(Class fieldClass) {
        if (getTestApplicationContext().containsBean(fieldClass.getSimpleName())) {
            return getTestApplicationContext().getBean(fieldClass);
        } else {
            return null;
        }
    }

    public static Object getApplicationBean(String name) {
        return getTestApplicationContext().getBean(name);
    }

    public static boolean hasBean(String name) {
        return getTestApplicationContext().containsBean(name);
    }

    public static void addBean(String name, Class value) {
        getTestApplicationContext().addBean(name, getBean(value));
    }

    public static void addBean(String name, Object value) {
        getTestApplicationContext().addBean(name, value);
    }

    public static Object getBean(String name) {
        return getTestApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> testService, Object... values) throws BeanException {
        T t = null;
        try {
            //使用默认构造方法创建对象，避免为private，使用反射将其设置为可访问
            Constructor c0 = testService.getDeclaredConstructor();
            c0.setAccessible(true);
            t = (T) c0.newInstance();
            Field[] fields = testService.getDeclaredFields();
            for (Field field : fields) {
                Class fieldClass;
                try {
                    fieldClass = Class.forName(field.getGenericType().getTypeName());
                    Annotation[] annotations = field.getAnnotations();
                    if (annotations != null && annotations.length > 0) {
                        Annotation annotation = annotations[0];
                        if (annotation instanceof Autowired
                                || annotation instanceof Resource
                                || annotation instanceof Qualifier) {
                            System.out.println("get bean, beanName is" + field.getAnnotatedType().getType().getTypeName());
                            if (IBaseDao.class.isAssignableFrom(fieldClass)
                                    || field.getGenericType().getTypeName().endsWith("Dao")
                                    || field.getGenericType().getTypeName().endsWith("Mapper")) {
                                Object daoClass = getTestApplicationContext().getBean(fieldClass.getName());
                                if (daoClass != null) {
                                    field.setAccessible(true);
                                    field.set(t, daoClass);
                                }
                            } else if (field.getGenericType().getTypeName().startsWith("com.jef")) {
                                if (values != null && values.length > 0) {
                                    for (Object obj : values) {
                                        if (obj != null && fieldClass.isInstance(obj)) {
                                            field.setAccessible(true);
                                            field.set(t, obj);
                                        }
                                    }

                                } else {
                                    try {
                                        Object obj = getTestApplicationContext().getBean(fieldClass);
                                        field.setAccessible(true);
                                        field.set(t, obj);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        } else if (annotation instanceof Reference) {
                            Object obj = getDubboService(fieldClass, 0);
                            field.setAccessible(true);
                            field.set(t, obj);
                        }
                    }

                } catch (Exception e) {
                    // service实现中存在int double时经常报错,service有些dao无需注入，便于快速测试
                    System.out.println("get beanName error, beanName is" + field.getGenericType().getTypeName() + ", errorMessage is " + e.getMessage());
                    continue;
                }
            }
            if (t != null) {
//                TestBeanUtil.addBean(testService.getSimpleName(),t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanException(e.getMessage());
        }
        return t;
    }

    static Map<Class<?>, Object> objMap = new HashMap<Class<?>, Object>();

    public static <T> T getDubboService(Class<T> className, long localPort) throws Exception {
        if (objMap.get(className) != null) {
            return (T) objMap.get(className);
        } else {
            if (localPort > 0) {
                System.setProperty(className.getTypeName(), "dubbo://127.0.0.1:" + localPort);
            }
            ReferenceConfig<T> referenceConfig = new ReferenceConfig<T>();
            referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
            RegistryConfig registryConfig = new RegistryConfig("zookeeper://172.17.1.132:2181");
            referenceConfig.setRegistry(registryConfig);
            referenceConfig.setInterface(className);
            referenceConfig.setTimeout(5000);
            referenceConfig.setVersion("1.0.2");
            referenceConfig.setGroup("dubbo");
            T obj = referenceConfig.get();
            objMap.put(className, obj);
            return obj;
        }
    }

    public static Object invoke(Object service, String methodName, Object... params) throws Exception {
        Method[] methods = service.getClass().getDeclaredMethods();
        Method tempMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                tempMethod = method;
            }
            if (method.getParameterCount() == params.length) {
                break;
            }
        }
        return tempMethod.invoke(service, params);
    }
}