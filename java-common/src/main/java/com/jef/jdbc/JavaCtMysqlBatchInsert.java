package com.jef.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaCtMysqlBatchInsert {
    public static void main(String[] args) throws SQLException {
        batchInsertV2();
    }

    public static void batchInsertV1() {
        try {
            Connection ct = ConnectionMySQL.getMySQLConnection();
            ct.setAutoCommit(false);
            Statement sm = ct.createStatement();
            sm.addBatch("insert into user(name, age) values('dage', 100)");
            sm.addBatch("insert into user(name, age) values('haonan', 100)");
            sm.executeBatch();
            sm.clearBatch();
            sm.close();
            ct.commit();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量插入，推荐
     * @author Jef
     * @date 2019/7/17
     * @param
     * @return void
     */
    public static void batchInsertV2() throws SQLException {
        Connection ct = ConnectionMySQL.getMySQLConnection();
        String sqlOne = "insert into user(name, age) values(?, ?)";
        PreparedStatement pst = ct.prepareStatement(sqlOne);
        pst.setString(1, "dage");
        pst.setInt(2, 3);
        pst.addBatch();
        pst.setString(1, "haonan");
        pst.setInt(2, 4);
        pst.addBatch();
        pst.setString(1, "Jef");
        pst.setInt(2, 4);
        pst.addBatch();
        pst.setString(1, "Ran");
        pst.setInt(2, 4);
        pst.addBatch();
        pst.executeBatch();
        pst.clearBatch();
        pst.close();
        ct.close();
    }
}
