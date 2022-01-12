package com.jef.test.common;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
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