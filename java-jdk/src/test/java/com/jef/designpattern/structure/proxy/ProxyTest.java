package com.jef.designpattern.structure.proxy;

import com.jef.constant.BasicConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 代理模式测试
 *
 * @author Jef
 * @date 2021/12/11
 */
public class ProxyTest {

    @Test
    public void testProxy() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("designpattern/spring-config.xml");
        IProxyUserDao userDao = beanFactory.getBean("proxyUserDao", IProxyUserDao.class);
        String res = userDao.queryUserInfo(BasicConstant.FIRST_USER_NUMBER);
        System.out.printf("测试结果：%s", res);
    }

}