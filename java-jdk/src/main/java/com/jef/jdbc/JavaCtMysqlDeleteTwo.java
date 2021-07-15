package com.jef.jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaCtMysqlDeleteTwo {
    public static void main(String[] args) {
        try {
            Connection ct = ConnectionMySQL.getMySQLConnection();
            Statement sm = ct.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = sm.executeQuery("select * from user where name='dage'");// 从查询到的内容中进行修改、插入和删除
            rs.last();
            rs.deleteRow();
            rs.close();
            sm.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
