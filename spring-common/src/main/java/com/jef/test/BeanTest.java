package com.jef.test;

import com.jef.common.utils.ContextUtils;
import com.jef.entity.ComplexObject;
import com.jef.entity.OrderInfo;
import com.jef.entity.User;
import com.jef.service.IPersonServive;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * Spring bean测试
 */
public class BeanTest {
    public static void main(String[] args) {

    }

    @Test
    public void useChopTest() {
        ApplicationContext applicationContext = ContextUtils.getContextFromBeansXML();
        // 获取 id="chineseService"的bean
        IPersonServive chineseService = applicationContext.getBean("chineseService", IPersonServive.class);
        // 调用 useAxe() 方法
        chineseService.useAxe();
        // 获取 id="oldChineseService"的bean
        IPersonServive oldChineseService = applicationContext.getBean("oldChineseService", IPersonServive.class);
        // 调用 useAxe() 方法
        oldChineseService.useAxe();
        // 获取 id="originalChineseService"的bean
        IPersonServive originalChineseService = applicationContext.getBean("originalChineseService", IPersonServive.class);
        // 调用 useAxe() 方法
        originalChineseService.useAxe();
    }

    @Test
    public void useBeanFactoryTest() {
        BeanFactory factory = ContextUtils.getBeanFactoryFromBeansXML();
        // 获取 id="chineseService"的bean
        IPersonServive chineseService = factory.getBean("chineseService", IPersonServive.class);
        // 调用 useAxe() 方法
        chineseService.useAxe();
        // 获取 id="chineseService"的bean
        IPersonServive cnForAndroidService = factory.getBean("cnForAndroidService", IPersonServive.class);
        // 调用 useAxe() 方法
        cnForAndroidService.useAxe();
        // 获取 id="chineseService"的bean
        IPersonServive cnForIOSService = factory.getBean("cnForIOSService", IPersonServive.class);
        // 调用 useAxe() 方法
        cnForIOSService.useAxe();
    }

    @Test
    public void useBeanFactoryTestByFileSystemReesource() {
        BeanFactory factory = ContextUtils.getBeanFactoryByFileSystemResource();
        // 获取 id="chineseService"的bean
        IPersonServive chineseService = factory.getBean("chineseService", IPersonServive.class);
        // 调用 useAxe() 方法
        chineseService.useAxe();
    }

    /**
     * 用户数据测试
     * @author Jef
     * @date 2020/11/29
     */
    @Test
    public void useBeanFactoryForUser() {
        BeanFactory factory = ContextUtils.getContextFromBeansXML("test/userBeans.xml");
        User userBean = factory.getBean("userConstructorArg", User.class);
        OrderInfo orderInfo = userBean.getOrderInfo();
        System.out.println("extraOrderId=" + orderInfo.getExtraOrderId());

        User userConstructorArgV2 = factory.getBean("userConstructorArgV2", User.class);
        OrderInfo orderInfoV2 = userConstructorArgV2.getOrderInfo();
        System.out.println("extraOrderId=" + orderInfoV2.getExtraOrderId());

        User userConstructorArgAndSetter = factory.getBean("userConstructorArgAndSetter", User.class);
        OrderInfo orderInfoV3 = userConstructorArgAndSetter.getOrderInfo();
        System.out.println("extraOrderId=" + orderInfoV3.getExtraOrderId());

        User user = factory.getBean("userAgePhoneBean", User.class);
        System.out.println(user.getName() + ":" + user.getAge() + "岁");

        User userAgePhoneBeanByIndex = factory.getBean("userAgePhoneBeanByIndex", User.class);
        System.out.println(userAgePhoneBeanByIndex.getName() + ":" + userAgePhoneBeanByIndex.getAge() + "岁");
    }

    /**
     * 复杂对象属性测试
    * @author Jef
     * @date 2020/11/29
     */
    @Test
    public void useComplexObject() {
        BeanFactory factory = ContextUtils.getContextFromBeansXML("test/objectBean.xml");
        ComplexObject complexObject = factory.getBean("moreComplexObject", ComplexObject.class);
        System.out.println(complexObject.getSomeList());
    }
}
