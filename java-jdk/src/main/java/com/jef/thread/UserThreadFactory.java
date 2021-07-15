package com.jef.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jef
 * @date 2019/11/1
 */
public class UserThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger nextId = new AtomicInteger(1);
        // 定义线程组名称，在 jstack 问题排查时，非常有帮助
        UserThreadFactory(String whatFeaturOfGroup) {
            namePrefix = "From UserThreadFactory's " + whatFeaturOfGroup + "-Worker-";
        }
        @Override
        public Thread newThread(Runnable task) {
            String name = namePrefix + nextId.getAndIncrement();
            Thread thread = new Thread(task, name);
            System.out.println(thread.getName());
            return thread;
    }
}