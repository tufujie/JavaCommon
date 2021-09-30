package com.jef.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Zookeeper监听器
 *
 * @author Jef
 * @date 2021/9/29
 */
public class ZookeeperWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event is【" + watchedEvent + "】");
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watcher Zookeeper state is 【" + Event.KeeperState.SyncConnected + "】");
        }
    }
}