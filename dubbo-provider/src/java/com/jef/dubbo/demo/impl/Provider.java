package com.jef.dubbo.demo.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * dubbo服务
 * @author Jef
 * @date 2021/3/4
 */
public class Provider {
        public static void main(String[] args) throws IOException {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
            context.start();
            System.out.println("dubbo服务已经启动...");
            System.in.read();
        }
    }

