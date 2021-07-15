package com.jef.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 第一个网络程序，获取当日时间，适用于非常简单的服务器，host: time-A.timefreq.bldrdoc.gov，port: 13
 */
public class SockketTest {
    private static final String HOST = "time-A.timefreq.bldrdoc.gov";
    private static final Integer PORT = 13;
    private static final Integer TIMEOUT = 10000;
    private static final String BAIDUHOST = "www.baidu.com";

    public static void main(String[] args) {
       /* socketTestOne();
        socketTestTwo();*/
       otherTest();
    }

    /**
     * 方式1建立socket通信
     */
    private static void socketTestOne() {
        try(Socket s = new Socket(HOST, PORT)) {

            // 设置超时值
            s.setSoTimeout(TIMEOUT);
            printInfo(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 方式2建立socket通信
     */
    private static void socketTestTwo() {
        Socket s = new Socket();
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(HOST, PORT);
            s.connect(inetSocketAddress, TIMEOUT);
            printInfo(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 相同的socket进行输出
     * @param s
     * @throws IOException
     */
    private static void printInfo(Socket s) throws IOException {
        InputStream inputStream = s.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
    }

    /**
     * 其它一些特性的测试
     */
    private static void otherTest() {
        try {
            // 得到百度的IP地址
            InetAddress[] addresses = InetAddress.getAllByName(BAIDUHOST);
            for(InetAddress address : addresses) {
                System.out.println("host address = " + address.getHostAddress());
                System.out.println("host name = " + address.getHostName());
            }
            // 得到本地的IP地址
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("host address = " + localHost.getHostAddress());
            System.out.println("host name = " + localHost.getHostName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


