package com.jef.thread.concurrencyInPractice;

import com.jef.entity.Person;

import javax.annotation.concurrent.GuardedBy;

/**
 * 私有锁
 * @author Jef
 * @date 2020/6/18 0018
 */
public class PrivateLock {

    private final Object myLock = new Object();

    @GuardedBy("myLock")
    Person person;

    void someMethod() {
        synchronized (myLock) {
            // 修改person的属性
            person.setAge(20);
            // ...
        }
    }

}