package com.jef.test.common;

import com.jef.dao.IBaseDao;
import com.jef.util.ApolloUtil;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableSet;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class TestDaoContext {
    Logger log = LoggerFactory.getLogger(TestDaoContext.class);
    SqlSessionFactory factory;
    TestTxManager testTxManager;
    DataSource dataSource;
    static TestDaoContext context;

    private TestDaoContext() {
    }

    public static TestDaoContext getInstance() {
        if (context == null) {
            context = new TestDaoContext();
        }
        return context;
    }

    public static TestTxManager getTestTxManager() {
        return getInstance().testTxManager;
    }

    public static <T> T getDao(Class<T> daoBean) {
        try {
            return getInstance().factory.getConfiguration().getMapper(daoBean, getInstance().factory.openSession());
        } catch (Exception ex) {
            return null;
        }
    }

    public DataSource getDataSource() throws Exception {
        if (dataSource == null) {
            Map map = ApolloUtil.getApolloConfig("application", "spring.datasource");
            // 密码加密后放入"spring.datasource.publicKey"到application中
            //  map.put("password", ConfigTools.decrypt((String) map.get("publicKey"), (String) map.get("password")));
            dataSource = getDataSource("com.alibaba.druid.pool.DruidDataSource", map);
            testTxManager = new TestTxManager(new DataSourceTransactionManager(dataSource));
        }
        return dataSource;
    }

    public void initMybatis(String... mappperPath) throws Exception {
        long starttime = System.currentTimeMillis();
        MybatisProperties properties = new MybatisProperties();
        properties.setTypeAliasesPackage("com.jef.entity.*");

        properties.setMapperLocations(mappperPath);
        Properties configurationProperties = new Properties();
        configurationProperties.put("lazy-loading-enabled", "true");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getDataSource());
        bean.setMapperLocations(properties.resolveMapperLocations());
        bean.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        bean.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        factory = bean.getObject();
        SqlSession sqlSession = factory.openSession();
        MapperRegistry registry = factory.getConfiguration().getMapperRegistry();
        Field field = MapperRegistry.class.getDeclaredField("knownMappers");
        field.setAccessible(true);
        Map<Class<?>, MapperProxyFactory<?>> knownMappers = (Map<Class<?>, MapperProxyFactory<?>>) field.get(registry);
        Iterator<Map.Entry<Class<?>, MapperProxyFactory<?>>> it = knownMappers.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Class<?>, MapperProxyFactory<?>> value = it.next();
            Class daoClass = value.getKey();
            MapperProxyFactory daoBean = value.getValue();
            Object obj = daoBean.newInstance(sqlSession);
            TestBeanUtil.addBean(daoClass.getName(), obj);
            if (IBaseDao.class.isAssignableFrom(daoClass)) {
                DaoContext.put(daoClass.getName(), (IBaseDao) obj);
            }
        }
        log.info("startup,mybatis,usetime,{}", System.currentTimeMillis() - starttime);
    }

    private static final String SET_METHOD_PREFIX = "set";
    private static final Collection<Class<?>> GENERAL_CLASS_TYPE = ImmutableSet.of(Boolean.TYPE, Boolean.class, Integer.TYPE, Integer.class, Long.TYPE, Long.class, String.class);

    public static DataSource getDataSource(String dataSourceClassName, Map<String, Object> dataSourceProperties) throws ReflectiveOperationException {
        DataSource result = (DataSource) Class.forName(dataSourceClassName).newInstance();
        for (Map.Entry<String, Object> entry : dataSourceProperties.entrySet()) {
            callSetterMethod(result, getSetterMethodName(entry.getKey()), null == entry.getValue() ? null : entry.getValue().toString());
        }

        return result;
    }

    private static String getSetterMethodName(String propertyName) {
        return propertyName.contains("-") ? CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "set-" + propertyName) : "set" + String.valueOf(propertyName.charAt(0)).toUpperCase() + propertyName.substring(1, propertyName.length());
    }

    private static void callSetterMethod(DataSource dataSource, String methodName, String setterValue) {
        boolean successSet = false;
        for (Class<?> each : GENERAL_CLASS_TYPE) {
            try {
                Method method = dataSource.getClass().getMethod(methodName, each);
                if (Boolean.TYPE == each || Boolean.class == each) {
                    method.invoke(dataSource, Boolean.valueOf(setterValue));
                } else if (Integer.TYPE == each || Integer.class == each) {
                    method.invoke(dataSource, Integer.parseInt(setterValue));
                } else if (Long.TYPE == each || Long.class == each) {
                    method.invoke(dataSource, Long.parseLong(setterValue));
                } else {
                    method.invoke(dataSource, setterValue);
                }

                return;
            } catch (ReflectiveOperationException var6) {
            }
        }
    }


    public void initTxManager() throws Exception {

    }

}