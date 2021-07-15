package com.jef.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaCtMysqlUpdate {
    public static void main(String[] args) throws Exception {
        updateV2();
    }

    /**
     * 更新方法1
     * @author Jef
     * @date 2019/2/14
     */
    private static void updateV1() throws SQLException {
        try {
            Connection ct = ConnectionMySQL.getMySQLConnection();
            Statement sm = ct.createStatement();
            sm.executeUpdate("update user set name='ran' where name='ranye'");
            sm.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新方法2
     * @author Jef
     * @date 2019/2/14
     */
    private static void updateV2() throws SQLException {
        Connection conn = ConnectionMySQL.getMySQLConnection();
        String sql = "update user set name=? where name=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "Ran");
        pstmt.setString(2, "ran");
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
