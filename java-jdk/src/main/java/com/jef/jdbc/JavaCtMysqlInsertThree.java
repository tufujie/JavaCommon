package com.jef.jdbc;

import java.sql.Connection;
import java.sql.Statement;

public class JavaCtMysqlInsertThree {
    public static void main(String[] args) {
        try {
            Connection ct = ConnectionMySQL.getMySQLConnection();
            ct.setAutoCommit(false);
            Statement sm = ct.createStatement();
            sm.executeUpdate("insert into user values('yuanyuan', '100')");
            ct.commit();
            sm.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
