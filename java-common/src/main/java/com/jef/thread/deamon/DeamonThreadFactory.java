package com.jef.thread.deamon;

import java.util.concurrent.ThreadFactory;

public class DeamonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    }
}
