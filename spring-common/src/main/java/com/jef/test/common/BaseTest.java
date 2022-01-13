package com.jef.test.common;

import com.jef.common.context.SpringContextHolder;
import com.jef.redis.RedisServiceFactory;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.env.MockEnvironment;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.net.URL;

public abstract class BaseTest {

    protected static Logger log;
    private static boolean useDatasource = false;

    public abstract void init() throws Exception;

    public <T> T getDubboService(Class<T> className, long localPort) throws Exception {
        return TestBeanUtil.getDubboService(className, localPort);
    }

    public void initCommon() throws Exception {
        initMybatis();
    }


    public Object getDao(Class daoBean) {
        try {
            return TestDaoContext.getDao(daoBean);
        } catch (Exception ex) {
            return null;
        }
    }

    public void initMybatis() throws Exception {
        initMybatis("mapper/*Mapper.xml");
    }

    public void initMybatis(String... mappperPath) {
        if (mappperPath != null && mappperPath.length > 0) {
            try {
                TestDaoContext.getInstance().initMybatis(mappperPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        useDatasource = true;
    }

    public void initContext(TestBean... beans) throws Exception {
        for (TestBean bean : beans) {
            TestBeanUtil.addBean(bean.getBeanName(), bean.getBeanClass());
        }
    }

    public void initMongoDb() throws Exception {
        TestBeanUtil.addBean("mongoTemplate", TestMongoTemplate.getMongoTemplate());
    }

    public void initRedis() throws Exception {
        long starttime = System.currentTimeMillis();
        JedisConnectionFactory conn = new JedisConnectionFactory();
        conn.setDatabase(0);
        conn.setHostName("127.0.0.1");
        conn.setPort(6379);
        conn.setPassword("root");
        conn.setUsePool(true);
        conn.afterPropertiesSet();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(conn);
        redisTemplate.afterPropertiesSet();

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(conn);
        stringRedisTemplate.afterPropertiesSet();

        //设置默认的app
        MockEnvironment env = (MockEnvironment) TestBeanUtil.getTestApplicationContext().getEnvironment();
        if (env.getProperty("spring.application.name") == null) {
            env.setProperty("spring.application.name", "spring-mvc-jef");
        }
        RedisServiceFactory factory = RedisServiceFactory.getSingleton();
        Field f1 = RedisServiceFactory.class.getDeclaredField("redisTemplate");
        f1.setAccessible(true);
        f1.set(factory, redisTemplate);


        Field f2 = RedisServiceFactory.class.getDeclaredField("stringRedisTemplate");
        f2.setAccessible(true);
        f2.set(factory, stringRedisTemplate);

        Field f3 = RedisServiceFactory.class.getDeclaredField("environment");
        f3.setAccessible(true);
        f3.set(factory, env);
        Config config = new Config();
        ((SingleServerConfig) config.useSingleServer().setAddress("redis://172.17.1.132:6379").setConnectTimeout(6000)).setDatabase(0).setPassword("root");
        RedissonClient redissonClient = Redisson.create(config);
        TestBeanUtil.addBean("redisson", redissonClient);
        TestBeanUtil.addBean("redissonLock", RedissonLock.class);
        log.info("startup,redis,usetime,{}", System.currentTimeMillis() - starttime);
    }

    public <T> T getBean(Class<T> beanClass) throws Exception {
        return TestBeanUtil.getBean(beanClass);
    }


    static long startTime;

    static {
        initLog();
    }

    private static void initLog() {
        try {
            System.out.println("===============开始执行测试案例=============");
            startTime = System.currentTimeMillis();
            String filePath = getFileClassPath("logtest.properties");
            File log4jFile = new File(filePath);
            if (log4jFile.exists()) {
                System.setProperty("log4j.configurationFactory", PropertiesConfigurationFactory.class.getName());
                ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
                Configurator.initialize(BaseTest.class.getClassLoader(), source);
                log = LoggerFactory.getLogger("JunitTest");
                log.debug("this is a debug");
                log.info("this is a info");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void commit() {
        if (useDatasource) {
            TestDaoContext.getTestTxManager().commit();
        }
    }

    @BeforeEach
    public void before() throws Exception {
        try {
            if (startTime == 0) {
                initLog();
            }
            System.setProperty("apollo.meta", "http://localhost:8080");
            System.setProperty("apollo.bootstrap.enabled", "false");
            System.setProperty("app.id", "spring-mvc-jef");
            new SpringContextHolder().setApplicationContext(TestBeanUtil.getTestApplicationContext());
            //执行其他初始化操作
            init();
            if (useDatasource) {
                TestDaoContext.getTestTxManager().begin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("startup,all,usetime,{}ms", System.currentTimeMillis() - startTime);
        }
    }

    @AfterEach
    public void after() {
        log.info("test,execute,usetime,{}ms", System.currentTimeMillis() - startTime);
        if (useDatasource) {
//            TestDaoContext.getTestTxManager().rollback();
        }
    }


    public static String getFileClassPath(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            String currentClasspath = resource.getPath();
            // 当前操作系统
            String osName = System.getProperty("os.name");
            if (osName.startsWith("Windows")) {
                // 删除path中最前面的/
                currentClasspath = currentClasspath.substring(1);
            }
            return currentClasspath;
        }
        return null;
    }

    public static String getCurrentClasspath() {
        return getFileClassPath("");
    }


}