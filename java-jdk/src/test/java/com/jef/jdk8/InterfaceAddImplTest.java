package com.jef.jdk8;

import org.junit.jupiter.api.Test;

/**
 * @author Administrator
 * @date 2022/12/20
 */
public class InterfaceAddImplTest implements InterfaceAdd {

    @Test
    public void testInterfaceMethod() {
        System.out.println(InterfaceAdd.staticFunction());
    }

}