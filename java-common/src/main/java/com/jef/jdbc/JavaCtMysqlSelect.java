package com.jef.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 当前情况下至少需要4+2条数据存在，特殊场景需捕获或者抛出
 */
public class JavaCtMysqlSelect {
    public static void main(String[] args) throws SQLException {
        new JavaCtMysqlSelect().selectV2();
    }

    public void selectV1() {
        try {
            Connection conn = ConnectionMySQL.getMySQLConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet result = stmt.executeQuery("select * from user");
            System.out.println("从末尾数据开始输出");
            result.last();
            System.out.println(result.getString("name") + "\t" + result.getString("password"));
            while (result.previous())
                System.out.println(result.getString("name") + "\t" + result.getString("password"));
            System.out.println("从起始数据开始输出");
            result.first();
            System.out.println(result.getString("name") + "\t" + result.getString("password"));
            while(result.next())
                System.out.println(result.getString("name") + "\t" + result.getString("password"));

            // 指定第几笔数据
            System.out.println("指定第几笔数据，这里指定第4笔数据");
            result.absolute(4);
            System.out.println(result.getString("name") + "\t" + result.getString("password"));

            // 从目前游标处指定游标下移数
            System.out.println("从目前游标处指定游标位置，这里向下移动2笔数据");
            result.relative(2);
            System.out.println(result.getString("name") + "\t" + result.getString("password"));
            result.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询的第二种方式
     * @author Jef
     * @date 2019/2/14
     * @param
     */
    public void selectV2() throws SQLException {
        Connection conn = ConnectionMySQL.getMySQLConnection();
        String sql = "select * from user where name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "test");
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            break;
        }
        pstmt.close();
        conn.close();
    }
}
