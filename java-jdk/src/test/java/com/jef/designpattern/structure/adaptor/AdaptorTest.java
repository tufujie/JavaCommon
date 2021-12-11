package com.jef.designpattern.structure.adaptor;

import org.junit.Test;

public class AdaptorTest {

    @Test
    public void useAdaptorTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("com.jef.designpattern.structure.adaptor.WomanAdaptor");
        IAdaptor adaptor = (IAdaptor) clazz.newInstance();
        System.out.println(adaptor.test());
    }

}