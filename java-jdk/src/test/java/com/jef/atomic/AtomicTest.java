package com.jef.atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jef
 * @date 2023/4/11
 */
public class AtomicTest {

    @Test
    public void testCompareAndSet() {
        AtomicInteger atomicInteger = new AtomicInteger();
        int value = atomicInteger.get();
        System.out.println("value=" + value);
        atomicInteger.set(1);
        value = atomicInteger.get();
        System.out.println("value=" + value);
        boolean updateFlag = atomicInteger.compareAndSet(2, 3);
        value = atomicInteger.get();
        System.out.println("updateFlag=" + updateFlag + ",value=" + value);
        // update table set value = update where value = expect
        /*
         * ABA问题解决
         * 使用版本号
         * select version from table where id = 1
         * update table set value = update, version = version + 1 where id = 1 and version = #{version}
         * */
        updateFlag = atomicInteger.compareAndSet(1, 3);
        value = atomicInteger.get();
        System.out.println("updateFlag=" + updateFlag + ",value=" + value);
    }

}