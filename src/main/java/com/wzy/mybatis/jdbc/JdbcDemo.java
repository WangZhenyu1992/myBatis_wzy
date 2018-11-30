package com.wzy.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        String url = "jdbc:mysql://localhost:3306/wzy_test";
        String user = "root";
        String password = "aA123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            stmt = connection.createStatement();
            String sql = "update user_info set password = 'abc' where id = 1;";
            stmt.execute(sql);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
