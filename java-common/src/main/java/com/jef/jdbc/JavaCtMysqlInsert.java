package com.jef.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * //演示如何使用com.mysql.jdbc连接桥连接Mysql，插入内容，共三种方式：静态的两种executeUpdate()，executeBatch()，动态的PrepareStatement，
 * 实时动态时可以采用PrepareStatement()和executeBatch()的结合，删除、修改都有这三种方法
 * setAutoCommit默认为true，如果设置为false，则需要手动添加代码commit
 */
public class JavaCtMysqlInsert {
    public static void main(String[] args) {
        try {
            Connection ct = ConnectionMySQL.getMySQLConnection();
            ct.setAutoCommit(false);
            PreparedStatement ps = ct.prepareStatement("insert into user(name, age) values(?, ?)");
            ps.setString(1, "yifeng");
            ps.setString(2, "100");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
            ct.commit();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
