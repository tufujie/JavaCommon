package com.jef.jdbc;

import java.sql.*;

//演示如何使用com.mysql.jdbc连接桥连接MySQL
public class JavaCtMySQL {
    public static void main(String[] args) {
        try {
            Connection con = ConnectionMySQL.getMySQLConnectionTwo();
            Statement sm = con.createStatement();
            String sql = "select * from user";
            // 3.跟sql server一模一样，创建statement对象，用来执行Sql语句
            ResultSet rs = sm.executeQuery(sql);
            while (rs.next()) {
                // 获取用户名和密码
                // 方式1，用下标的形式，从1开始
                System.out.println("管理员名字：" + rs.getString(1) + "密码：" + rs.getString(2));
                // 方式2，根据字段名称获取
//                System.out.println("管理员名字：" + rs.getString("name") + "密码：" + rs.getString("password"));
            }
            // 关闭打开的资源
            rs.close();
            sm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}