package com.jef.thread.deamon;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DeamonThreadPoolExecutor extends ThreadPoolExecutor {
    public DeamonThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new DeamonThreadFactory());
    }
}
