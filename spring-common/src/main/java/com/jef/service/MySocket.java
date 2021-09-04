package com.jef.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * 现在来说说Servlet的监听器Listener,它是实现了javax.servlet.ServletContextListener 接口的
 * 服务器端程序,它也是随web应用的启动而启动,只初始化一次,随web应用的停止而销毁。
 * 主要作用是:做一些初始化的内容添加工作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等。
 */
public class MySocket implements ServletContextListener {
    private static Logger logger = LogManager.getLogger(MySocket.class);

    // 定义保存所有的Socket
    public static List<Socket> socketList = new ArrayList<Socket>();
    public int port = 43333;
    public Thread thread;

    /**
     * 应用监听器的初始化方法
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("启动了一个监听，启动了线程");
        thread = new Thread() {
            @Override
            public void run() {
                ServerSocket server;
                try {
                    server = new ServerSocket(port);
                    while (true) {
                        logger.info("socket等待中……");
                        Socket socket = server.accept();
                        socketList.add(socket);
                        new Thread(new SocketService(socket)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();
    }

    /**
     * 应用监听器的销毁方法
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        thread.interrupt();
        logger.info("销毁工作完成…");
    }
}
