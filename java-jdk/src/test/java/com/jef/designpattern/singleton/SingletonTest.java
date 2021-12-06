package com.jef.designpattern.singleton;

import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2021/12/6
 */
public class SingletonTest {

    @Test
    public void testSingleton() {
        Singleton singleton = Singleton.getInstance();
        PrintUtil.printLineSplit();
        singleton = Singleton.getInstance();
        PrintUtil.printLineSplit();
    }

}