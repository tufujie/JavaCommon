package com.jef.test.common;

/**
 * @author Jef
 * @date 2022/1/12
 */
public class TestBean {
    String beanName;
    Class beanClass;
    public TestBean(String beanName,Class beanClass){
        this.beanName = beanName;
        this.beanClass = beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public static TestBean build(String beanName, Class beanClass){
        return new TestBean(beanName,beanClass);
    }
}