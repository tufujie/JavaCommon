package com.jef.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionMySQL {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionMySQL.class);
    /**
     * 数据库连接
     */
    private static Connection connection = null;

    public static synchronized Connection getMySQLConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                // 动态获取是否开发环境
                boolean isDev = true;
                // 默认是生产数据库
                // 数据库IP地址
                String ip = "127.0.0.1";
                // 端口号
                String port = "3306";
                // 数据库
                String db = "all_test";
                // MySQL配置时的用户名
                String user = "root";
                // MySQL配置时的密码
                String password = "root";
                if (isDev) {
                    // 开发环境
                    ip = "localhost";
                    // 端口号
                    port = "3306";
                    // 数据库
                    db = "all_test";
                    // MySQL配置时的用户名
                    user = "root";
                    // MySQL配置时的密码
                    password = "root";
                }
                // 1.加载（载入）驱动程序
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
                // 2.得到连接（注册），连接MySQL数据库
                connection = DriverManager.getConnection(url, user, password);
                if (connection != null && !connection.isClosed()) {
                    return connection;
                }
            }
        } catch (Exception e) {
            logger.info("数据库连接异常,异常信息={}", e.getMessage(), e);
        }
        return null;
    }

    public static Connection getMySQLConnectionTwo() throws IOException {
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("src\\com\\jef\\jdbc\\database.properties"))) {
            properties.load(in);
        }
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver =  properties .getProperty("jdbc.driver");
        //URL指向要访问的数据库名test并声明编码格式
        String url = properties.getProperty("jdbc.url");
        //MySQL配置时的用户名
        String user = properties.getProperty("jdbc.user");
        //MySQL配置时的密码
        String password = properties.getProperty("jdbc.password");
        //遍历查询结果集
        try {
            // 1.加载（载入）驱动程序
            Class.forName(driver);
            // 2.得到连接（注册），连接MySQL数据库
            con = DriverManager.getConnection(url, user, password);
            // 或者采用下面这种方式也行
            /*con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/test?user=tufujie&password=123456&useUnicode=true&characterEnconding=UTF-8");*/
            if (con != null && !con.isClosed()) {
                System.out.println("数据库测试连接成功");
                return con;
            }
        } catch (ClassNotFoundException  e) {
            //数据库驱动类异常处理
            System.out.println("未找到驱动程序");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
