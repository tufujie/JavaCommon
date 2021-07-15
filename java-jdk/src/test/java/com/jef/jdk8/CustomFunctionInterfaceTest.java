package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import org.junit.Test;

/**
 * 自定义函数式接口
 * @author Jef
 * @date 2021/2/20
 */
public class CustomFunctionInterfaceTest {
    @Test
    public void testCustomFunctionInteface() {
        test(() -> BasicConstant.HELLO_WORLD);
    }

    /**
     * 演示自定义函数式接口使用
     * @param worker
     */
    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }
}