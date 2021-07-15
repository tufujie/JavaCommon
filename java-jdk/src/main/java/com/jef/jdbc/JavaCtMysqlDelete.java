package com.jef.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaCtMysqlDelete {
    public static void main(String[] args) throws SQLException {
        deleteV2();
    }

    /**
     * 删除方法1
     * @author Jef
     * @date 2019/2/14
     */
    private void deleteV1() {
        try {
            Connection ct = ConnectionMySQL.getMySQLConnection();
            Statement sm = ct.createStatement();
            sm.executeUpdate("delete from user where name='tufujie'");
            sm.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除方法2
     * @author Jef
     * @date 2019/2/14
     */
    private static void deleteV2() throws SQLException {
        Connection conn = ConnectionMySQL.getMySQLConnection();
        String sql = "delete from user where name=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "Ran");
        pstmt.executeUpdate();
        // 这两句可以放在最后处理
        pstmt.close();
        conn.close();
        conn = ConnectionMySQL.getMySQLConnection();
        pstmt.setString(1, "tufujie");
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
